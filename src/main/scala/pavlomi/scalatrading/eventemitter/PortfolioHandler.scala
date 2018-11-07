package pavlomi.scalatrading.eventemitter
import pavlomi.scalatrading.domain.{Event, Position, StockSymbol}

abstract class PortfolioHandler[IN <: Event, OUT <: Event] extends EventEmitter[IN, OUT] {
  def getOpenPositions(symbol: StockSymbol): Seq[Position]

  protected def upsertPosition(position: Position): Unit
}
