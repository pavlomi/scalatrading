package pavlomi.scalatrading.domain

sealed trait Event { self =>
  def +(e: Event): CombinedEvent = CombinedEvent(self, e)
}

case class CombinedEvent(first: Event, second: Event) extends Event

object EmptyEvent extends Event

trait PositionEvent extends Event {
  def price: Price
  def symbol: StockSymbol
  def value: Value
  def direction: PositionDirection
}

trait DataEvent extends Event {
  def candlestick: CandlestickMarker
}

trait OrderEvent extends Event {
  def price: Price
  def symbol: StockSymbol
  def direction: PositionDirection
  def value: Value
}

trait SignalEvent extends Event {
  def symbol: StockSymbol
  def direction: PositionDirection
  def price: Price
}

trait StopLostEvent extends Event {
  def position: Position
}

trait TakeProfitEvent extends Event {
  def position: Position
}
