package pavlomi.scalatrading.domain

trait Position {
  def uuid: PositionId
  def open: Price
  def close: Option[Price]
  def value: Value
  def symbol: StockSymbol
  def direction: PositionDirection
  def subPosition: Seq[Position]
}
