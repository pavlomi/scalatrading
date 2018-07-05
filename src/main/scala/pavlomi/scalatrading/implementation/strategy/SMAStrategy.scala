package pavlomi.scalatrading.implementation.strategy

import pavlomi.scalatrading.domain._
import pavlomi.scalatrading.eventemitter.Strategy
import pavlomi.scalatrading.indicator.SMA

class SMAStrategy(private val intervalSMA: Int = 30) extends Strategy {
  override def execute(event: DataEvent): Seq[SignalEvent] = {
    Strategy.addItem(event.candlestick)
    val (candlestick, positionOpt) = DataEvent.unapply(event).get
    val stockSymbol                = candlestick.symbol

    val data         = Strategy.get(stockSymbol)
    val previousData = data.tail
    val previousSMA  = SMA.calculate(previousData, intervalSMA)

    if (previousSMA != 0) {
      val currentSMA = SMA.calculate(data, intervalSMA)
      (positionOpt, previousSMA < currentSMA) match {
        case (None, true) =>
          Seq(SignalEvent(stockSymbol, PositionDirection.Buy, Some(candlestick.close), None))
        case (None, false) =>
          Seq(SignalEvent(stockSymbol, PositionDirection.Sell, Some(candlestick.close), None))
        case (Some(p), true) if p.direction == PositionDirection.Buy =>
          Seq.empty[SignalEvent]
        case (Some(p), true) if p.direction == PositionDirection.Sell =>
          Seq(SignalEvent(stockSymbol, PositionDirection.Buy, Some(candlestick.close), Some(p)))
        case (Some(p), false) if p.direction == PositionDirection.Buy =>
          Seq(SignalEvent(stockSymbol, PositionDirection.Sell, Some(candlestick.close), Some(p)))
        case (Some(p), false) if p.direction == PositionDirection.Sell =>
          Seq.empty[SignalEvent]
      }
    } else Seq.empty[SignalEvent]

  }
}
