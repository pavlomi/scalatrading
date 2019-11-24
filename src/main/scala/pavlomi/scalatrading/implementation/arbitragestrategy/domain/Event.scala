package pavlomi.scalatrading.implementation.arbitragestrategy.domain
import java.util.UUID

import pavlomi.scalatrading.domain._
import pavlomi.scalatrading.implementation.arbitragestrategy.ArbitrageStrategy.Arbitrage3Step

case class ArbitrageDataEvent(
  arbitrage3Step: Arbitrage3Step,
  candlestick: CandlestickMarker = EmptyCandlestick
) extends DataEvent

case class ArbitrageSignalEvent(
  symbol: StockSymbol,
  direction: PositionDirection,
  price: Price,
  value: Value,
  number: UUID,
  step: Int
) extends SignalEvent

case class ArbitrageOrderEvent(
  symbol: StockSymbol,
  direction: PositionDirection,
  price: Price,
  value: Value,
  number: UUID,
  step: Int
) extends OrderEvent

case class ArbitragePositionEvent(
  price: Price,
  symbol: StockSymbol,
  value: Value,
  direction: PositionDirection,
  number: UUID,
  step: Int
) extends PositionEvent
