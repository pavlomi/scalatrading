package pavlomi.scalatrading.utils.factory
import java.time.Instant

import pavlomi.scalatrading.domain._

import scala.util.Random

object CandlestickFactory {
  val random = new Random()

  def create(
    open: BigDecimal = random.nextDouble,
    close: BigDecimal = random.nextDouble,
    high: BigDecimal = random.nextDouble,
    low: BigDecimal = random.nextDouble,
    stockSymbol: StockSymbol = StockSymbol("IBM"),
    value: BigDecimal = random.nextDouble,
    instant: Instant = Instant.now
  ): Candlestick = Candlestick(
    stockSymbol,
    Price(open),
    Price(close),
    Price(high),
    Price(low),
    Value(value),
    instant
  )
}
