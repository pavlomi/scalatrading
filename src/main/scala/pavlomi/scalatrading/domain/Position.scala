package pavlomi.scalatrading.domain
import java.time.Instant

trait Position {
  def uuid: PositionId
  def open: Price
  def openInstant: Instant
  def close: Option[Price]
  def closeInstant: Option[Instant]
  def value: Value
  def symbol: StockSymbol
  def direction: PositionDirection
  def subPosition: Seq[Position]
}
