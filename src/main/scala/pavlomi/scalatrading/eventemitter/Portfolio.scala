package pavlomi.scalatrading.eventemitter

import pavlomi.scalatrading.domain.{EmptyEvent, Position, PositionEvent, StockSymbol}

abstract class Portfolio extends EventEmitter[PositionEvent, EmptyEvent.type] {
  def getOpenPositions(symbol: StockSymbol): Seq[Position]
}
