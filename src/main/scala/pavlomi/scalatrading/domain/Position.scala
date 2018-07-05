package pavlomi.scalatrading.domain

trait Position {
  def open: Price
  def close: Option[Price]
  def value: BigInt
  def symbol: StockSymbol
  def direction: PositionDirection
}
