package pavlomi.scalatrading.domain

sealed trait Event

case class PositionEvent(
  price: Price,
  symbol: StockSymbol,
  direction: PositionDirection,
  position: Option[Position]
) extends Event

case class DataEvent(
  candlestick: Candlestick,
  position: Option[Position]
) extends Event

case class MarketEvent(
  price: Price,
  symbol: StockSymbol,
  direction: PositionDirection,
  position: Option[Position]
) extends Event

case class SignalEvent(
  symbol: StockSymbol,
  direction: PositionDirection,
  price: Option[Price],
  position: Option[Position]
) extends Event

case class SetStopLostEvent(position: Position)   extends Event
case class SetTakeProfitEvent(position: Position) extends Event

object EmptyEvent extends Event
