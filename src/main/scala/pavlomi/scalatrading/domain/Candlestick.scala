package pavlomi.scalatrading.domain

import java.time.Instant

trait CandlestickMarker

case class Candlestick(
  symbol: StockSymbol,
  open: Price,
  close: Price,
  high: Price,
  low: Price,
  value: Value,
  instant: Instant,
  subCandlestick: Seq[Candlestick] = Seq.empty[Candlestick]
) extends  CandlestickMarker {
  require(open.value >= 0)
  require(close.value >= 0)
  require(high.value >= 0)
  require(low.value >= 0)
  require(value.value >= 0)
}

case object EmptyCandlestick extends CandlestickMarker
