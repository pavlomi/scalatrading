package pavlomi.scalatrading.implementation.strategy

import org.scalatest.FlatSpec
import pavlomi.scalatrading.domain.{DataEvent, PositionDirection, Price, StockSymbol}
import pavlomi.scalatrading.implementation.domain.PositionImpl
import pavlomi.scalatrading.utils.factory.CandlestickFactory

import scala.util.Random

class SMAStrategySpec extends FlatSpec {
  val random = new Random()

  behavior of "SMAStrategy"

  it must "return " in {
    val stockSymbol = StockSymbol(random.nextString(4))
    val candlesticksUp = (100 to 400 by 100).map { a =>
      CandlestickFactory.create(
        close = a,
        open = (a - 10),
        high = (a + 10),
        low = (a - 20),
        stockSymbol = stockSymbol
      )
    }
    val dataEvents = candlesticksUp.map(DataEvent(_, None))

    val sMAStrategy = new SMAStrategy(3)

    val eventSeq1 = dataEvents.flatMap(sMAStrategy.execute(_))
    assert(eventSeq1.size == 1)
    assert(eventSeq1.head.direction == PositionDirection.Buy)
    assert(eventSeq1.head.price == Some(candlesticksUp.last.close))

    val position = PositionImpl(Price(100), None, 100, stockSymbol, PositionDirection.Buy)

    val candlesticksDown = (200 to 300 by 100).reverse.map { a =>
      CandlestickFactory.create(
        close = a,
        open = (a + 10),
        high = (a + 20),
        low = (a - 10),
        stockSymbol = stockSymbol
      )
    }

    val eventSeq2 = candlesticksDown.map(DataEvent(_, Some(position))).flatMap(sMAStrategy.execute(_))
    assert(eventSeq2.size == 1)
    assert(eventSeq2.head.direction == PositionDirection.Sell)
    assert(eventSeq2.head.price == Some(candlesticksDown.last.close))
  }
}
