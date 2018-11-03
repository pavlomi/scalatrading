package pavlomi.scalatrading.implementation.eventemitter

import pavlomi.scalatrading.domain._
import pavlomi.scalatrading.eventemitter.Strategy
import pavlomi.scalatrading.implementation.domain.{MADataEvent, MASignalEvent}
import pavlomi.scalatrading.indicator.SMA

class MAStrategy(private val intervalMA: Int = 30) extends Strategy[MADataEvent, Event] {

  override def execute(event: MADataEvent): Event = {
    Strategy.addItem(event.candlestick)
    val candlesticks = Strategy.get(event.candlestick.symbol)

    val sma      = SMA(candlesticks, intervalMA)
    val smaValue = sma.calculate

    if (smaValue != -1) {
      event match {
        case MADataEvent(candlestick, None) =>
          if (candlestick.low.value < smaValue && candlestick.close.value > smaValue) // Buy
            MASignalEvent(event.candlestick.symbol, PositionDirection.Buy, candlestick.close)
          else if (candlestick.high.value > smaValue && candlestick.close.value < smaValue) //Sell
            MASignalEvent(event.candlestick.symbol, PositionDirection.Sell, candlestick.close)
          else EmptyEvent
        case MADataEvent(candlestick, Some(position)) =>
          if (candlestick.close.value > smaValue && position.direction.isShort)
            MASignalEvent(event.candlestick.symbol, PositionDirection.Buy, candlestick.close, event.position)
          else if (candlestick.close.value < smaValue && position.direction.isLong)
            MASignalEvent(event.candlestick.symbol, PositionDirection.Sell, candlestick.close, event.position)
          else EmptyEvent
      }
    } else EmptyEvent
  }
}
