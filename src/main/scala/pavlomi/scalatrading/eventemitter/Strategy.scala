package pavlomi.scalatrading.eventemitter
import pavlomi.scalatrading.domain.{Candlestick, Event, StockSymbol}

abstract class Strategy[IN <: Event, OUT <: Event] extends EventEmitter[IN, OUT]

object Strategy {

  import scala.collection.mutable.Map

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
