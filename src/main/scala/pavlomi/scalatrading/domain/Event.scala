package pavlomi.scalatrading.domain

sealed trait Event

object EmptyEvent extends Event

trait PositionEvent extends Event {
  def price: Price
  def symbol: StockSymbol
  def value: Value
  def direction: PositionDirection
}

trait DataEvent extends Event {
  def candlestick: Candlestick
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
