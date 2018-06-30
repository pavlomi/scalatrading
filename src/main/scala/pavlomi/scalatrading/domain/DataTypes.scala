package pavlomi.scalatrading.domain

import enumeratum.{Enum, EnumEntry}

case class StockSymbol(value: String) extends AnyVal
case class Price(value: BigDecimal) extends AnyVal
case class Value(value: BigDecimal) extends AnyVal

sealed trait PositionDirection extends EnumEntry
object PositionDirection extends Enum[PositionDirection] {
  case object Buy extends PositionDirection
  case object Sell extends PositionDirection

  val values = findValues
}
