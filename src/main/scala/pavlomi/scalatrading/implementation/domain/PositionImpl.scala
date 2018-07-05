package pavlomi.scalatrading.implementation.domain

import pavlomi.scalatrading.domain._

case class PositionImpl(
  val open: Price,
  val close: Option[Price],
  val value: BigInt,
  val symbol: StockSymbol,
  val direction: PositionDirection
) extends Position
