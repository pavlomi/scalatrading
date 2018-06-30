package pavlomi.scalatrading.domain

import java.time.Instant

trait Candlestick {
  val symbol: StockSymbol
  val open: Price
  val close: Price
  val high: Price
  val low: Price
  val value: Value
  val instant: Instant
  val subCandlestick: Seq[Candlestick] = Seq.empty[Candlestick]
}

case class CandlestickImpl(
    symbol: StockSymbol,
    open: Price,
    close: Price,
    high: Price,
    low: Price,
    value: Value,
    instant: Instant
) extends Candlestick {
  override val subCandlestick: Seq[Candlestick] = Seq.empty[CandlestickImpl]
}
