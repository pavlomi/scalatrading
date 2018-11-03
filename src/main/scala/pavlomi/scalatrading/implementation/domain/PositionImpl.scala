package pavlomi.scalatrading.implementation.domain
import pavlomi.scalatrading.domain._

case class PositionImpl(
  val uuid: PositionId,
  val open: Price,
  val close: Option[Price],
  val value: Value,
  val symbol: StockSymbol,
  val direction: PositionDirection,
  val subPosition: Seq[Position] = Seq.empty[Position]
) extends Position

case class MADataEvent(val candlestick: Candlestick, position: Option[Position] = None) extends DataEvent

case class MASignalEvent(
  val symbol: StockSymbol,
  val direction: PositionDirection,
  val price: Price,
  position: Option[Position] = None
) extends SignalEvent

case class MAOrderEvent(
  val price: Price,
  val symbol: StockSymbol,
  val direction: PositionDirection,
  val value: Value,
  position: Option[Position] = None
) extends OrderEvent

case class MAPositionEvent(
  val price: Price,
  val symbol: StockSymbol,
  val direction: PositionDirection,
  val value: Value,
  position: Option[Position] = None
) extends PositionEvent
