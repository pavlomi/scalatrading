package pavlomi.scalatrading.domain

import java.util.UUID

import enumeratum.{Enum, EnumEntry}

case class StockSymbol(value: String) extends AnyVal
case class Price(value: BigDecimal)   extends AnyVal
case class Value(value: BigDecimal)   extends AnyVal

case class PositionId(value: UUID) extends AnyVal
object PositionId {
  def generate: PositionId = PositionId(UUID.randomUUID)
}

sealed trait PositionDirection extends EnumEntry { self =>
  def isLong: Boolean = self == PositionDirection.Buy

  def isShort: Boolean = !isLong

  def revert: PositionDirection = if (isLong) PositionDirection.Sell else PositionDirection.Buy
}
object PositionDirection extends Enum[PositionDirection] {
  case object Buy  extends PositionDirection
  case object Sell extends PositionDirection

  val values = findValues
}
