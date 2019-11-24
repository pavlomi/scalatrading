package pavlomi.scalatrading.implementation.mastrategy.eventemitter

import pavlomi.scalatrading.domain._
import pavlomi.scalatrading.eventemitter.Strategy
import pavlomi.scalatrading.implementation.mastrategy.domain.dto.MAStopLossDto
import pavlomi.scalatrading.implementation.mastrategy.domain.{MADataEvent, MASignalEvent, SLLimit, SLStop}
import pavlomi.scalatrading.indicator.SMA

class MAStrategyHandler(private val intervalMA: Int = 150, portfolioRepository: MAPortfolioRepository) extends Strategy[MADataEvent, Event] {

  override def execute(event: MADataEvent): Event = {
    Strategy.addItem(event.candlestick)
    val candlesticks = Strategy.get(event.candlestick.symbol)

    val sma      = SMA(candlesticks, intervalMA)
    val smaValue = sma.calculate

    val candlestick = event.candlestick

    val test = if (smaValue != -1) {
      portfolioRepository.findOpenByStockSymbol(event.candlestick.symbol) match {
        case None =>
          if (candlestick.low.value < smaValue && candlestick.close.value > smaValue) // Buy
            {
              val stopLossDto = getStopLossLevel(smaValue, PositionDirection.Buy)
              MASignalEvent(event.candlestick.symbol, PositionDirection.Buy, candlestick.close, event.candlestick.instant, Some(stopLossDto))
            } else if (candlestick.high.value > smaValue && candlestick.close.value < smaValue) // Sell
            {
              val stopLossDto = getStopLossLevel(smaValue, PositionDirection.Sell)
              MASignalEvent(event.candlestick.symbol, PositionDirection.Sell, candlestick.close, event.candlestick.instant, Some(stopLossDto))
            } else EmptyEvent
        case Some(position) =>
          if (candlestick.close.value > smaValue && position.direction.isShort) // Buy
            {
              val stopLossDto = getStopLossLevel(smaValue, PositionDirection.Buy)
              MASignalEvent(event.candlestick.symbol, PositionDirection.Buy, candlestick.close, event.candlestick.instant, None, Some(position)) +
                MASignalEvent(event.candlestick.symbol, PositionDirection.Buy, candlestick.close, event.candlestick.instant, Some(stopLossDto), None)
            } else if (candlestick.close.value < smaValue && position.direction.isLong) // Sell
            {

              val stopLossDto = getStopLossLevel(smaValue, PositionDirection.Sell)
              MASignalEvent(event.candlestick.symbol, PositionDirection.Sell, candlestick.close, event.candlestick.instant, None, Some(position)) +
                MASignalEvent(event.candlestick.symbol, PositionDirection.Sell, candlestick.close, event.candlestick.instant, Some(stopLossDto), None)
            } else EmptyEvent
      }
    } else EmptyEvent

    test match {
      case EmptyEvent => ()
      case _ =>
//        println("-------------------------------------------------------------")
//        println(test)
//        println(candlestick)
//        println(smaValue)
//        println("?????????????????????????????????????????????????????????????")
        ()
    }
    test
  }

  private def getStopLossLevel(smaValue: BigDecimal, positionDirection: PositionDirection): MAStopLossDto =
    positionDirection match {
      case PositionDirection.Sell =>
        val limit = smaValue
        val stop  = limit * 1.005
        MAStopLossDto(SLLimit(limit), SLStop(stop))
      case PositionDirection.Buy =>
        val limit = smaValue
        val stop  = limit * 0.995
        MAStopLossDto(SLLimit(limit), SLStop(stop))
    }

}
