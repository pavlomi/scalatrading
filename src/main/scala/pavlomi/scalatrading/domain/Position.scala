package pavlomi.scalatrading.domain

trait Position {
  def price: BigDecimal
  def symbol: StockSymbol
  def direction: PositionDirection
}
