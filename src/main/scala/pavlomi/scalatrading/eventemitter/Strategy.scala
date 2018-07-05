package pavlomi.scalatrading.eventemitter

import pavlomi.scalatrading.domain._

import scala.collection.mutable.Map

abstract class Strategy extends EventEmitter[DataEvent, SignalEvent]

object Strategy {
  private val candlesticksMap: Map[StockSymbol, Seq[Candlestick]] =
    Map.empty[StockSymbol, Seq[Candlestick]]

  def addItem(candlestick: Candlestick): Unit = {
    val stockSymbol = candlestick.symbol
    val candlesticks = candlesticksMap
      .get(stockSymbol)
      .map(candlestick +: _)
      .getOrElse(Seq(candlestick))

    candlesticksMap += (stockSymbol -> candlesticks)

  }

  def getLast(stockSymbol: StockSymbol): Option[Candlestick] =
    candlesticksMap.get(stockSymbol).flatMap {
      case c :: _ => Some(c)
      case Nil    => None
    }

  def get(stockSymbol: StockSymbol): Seq[Candlestick] =
    candlesticksMap.get(stockSymbol).getOrElse(Seq.empty[Candlestick])

  def clear = candlesticksMap.clear()
}
