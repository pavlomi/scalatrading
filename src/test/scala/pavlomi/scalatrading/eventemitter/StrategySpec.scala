package pavlomi.scalatrading.eventemitter

import org.scalatest.FlatSpec
import pavlomi.scalatrading.domain.{Candlestick, StockSymbol}
import pavlomi.scalatrading.utils.factory.CandlestickFactory

import scala.util.Random

class StrategySpec extends FlatSpec {
  behavior of "Strategy data store"

  val random = new Random()

  "getLast" must "return the last added element" in {
    val symbol      = random.nextString(3).toUpperCase
    val stockSymbol = StockSymbol(symbol)

    val (cs1, cs2, cs3) = addCandlestick(stockSymbol)

    assert(Strategy.getLast(stockSymbol) == Some(cs3))
    assert(Strategy.getLast(stockSymbol) != Some(cs2))
    assert(Strategy.getLast(stockSymbol) != Some(cs1))
  }

  "get" must "return the list of candlestick object" in {
    val symbol      = random.nextString(3).toUpperCase
    val stockSymbol = StockSymbol(symbol)

    val (cs1, cs2, cs3) = addCandlestick(stockSymbol)

    assert(Strategy.get(stockSymbol) == Seq(cs3, cs2, cs1))
    assert(Strategy.get(stockSymbol) != Seq(cs3, cs1, cs2))
    assert(Strategy.get(stockSymbol) != Seq(cs3, cs1))
  }

  "get" must "return a empty list" in {
    val wrongSymbol      = random.nextString(10).toUpperCase
    val symbol           = random.nextString(10).toUpperCase
    val stockSymbol      = StockSymbol(symbol)
    val wrongStockSymbol = StockSymbol(wrongSymbol)

    addCandlestick(stockSymbol)

    assert(Strategy.get(wrongStockSymbol).isEmpty)
    assert(Strategy.get(stockSymbol).nonEmpty)
  }

  private def addCandlestick(stockSymbol: StockSymbol): (Candlestick, Candlestick, Candlestick) = {
    val cs1 = CandlestickFactory.create(stockSymbol = stockSymbol)
    val cs2 = CandlestickFactory.create(stockSymbol = stockSymbol)
    val cs3 = CandlestickFactory.create(stockSymbol = stockSymbol)

    Strategy.addItem(cs1)
    Strategy.addItem(cs2)
    Strategy.addItem(cs3)

    (cs1, cs2, cs3)
  }

}
