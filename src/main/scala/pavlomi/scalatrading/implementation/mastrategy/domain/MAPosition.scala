package pavlomi.scalatrading.implementation.mastrategy.domain
import java.time.Instant

import pavlomi.scalatrading.domain._

case class MAPosition(
  uuid: PositionId,
  open: Price,
  openInstant: Instant,
  close: Option[Price],
  closeInstant: Option[Instant],
  value: Value,
  symbol: StockSymbol,
  direction: PositionDirection,
  subPosition: Seq[Position] = Seq.empty[MAPosition],
  stopLoss: MAStopLoss
) extends Position {
  import MAPosition.INVALID_CALCULATION

  private val calculateClosePrice: BigDecimal =
    direction match {
      case PositionDirection.Buy =>
        close.map(c => if (c.value < stopLoss.stop.value) stopLoss.stop.value else c.value).getOrElse(BigDecimal(INVALID_CALCULATION))
      case PositionDirection.Sell =>
        close.map(c => if (c.value > stopLoss.stop.value) stopLoss.stop.value else c.value).getOrElse(BigDecimal(INVALID_CALCULATION))
    }

  // Diff equals -1 if position not closed.
  val diff: BigDecimal =
    direction match {
      case PositionDirection.Buy  => if (calculateClosePrice == INVALID_CALCULATION) INVALID_CALCULATION else calculateClosePrice - open.value
      case PositionDirection.Sell => if (calculateClosePrice == INVALID_CALCULATION) INVALID_CALCULATION else open.value - calculateClosePrice
    }

  // Income equals -1 if position not closed.
  val income: BigDecimal = if (diff == -1) -1 else diff * value.value

}

object MAPosition {

  val INVALID_CALCULATION = -1
}
