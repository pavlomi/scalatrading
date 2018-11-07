package pavlomi.scalatrading.implementation.eventemitter

import pavlomi.scalatrading.domain.{EmptyEvent, Position, PositionId, StockSymbol}
import pavlomi.scalatrading.eventemitter.PortfolioHandler
import pavlomi.scalatrading.implementation.domain.{MAPosition, MAPositionEvent}

class MAPortfolioHandler(MAPortfolioRepository: MAPortfolioRepository) extends PortfolioHandler[MAPositionEvent, EmptyEvent.type] {

  override def execute(event: MAPositionEvent): EmptyEvent.type =
    event.position match {
      case Some(position) =>
        val updatedPosition = position.copy(close = Some(event.price))
        MAPortfolioRepository.update(updatedPosition.uuid, updatedPosition)
        EmptyEvent
      case None =>
        val position = MAPosition(PositionId.generate, event.price, None, event.value, event.symbol, event.direction)
        MAPortfolioRepository.insert(position)
        EmptyEvent
    }

  override def getOpenPositions(symbol: StockSymbol): Seq[MAPosition] =
    MAPortfolioRepository.findOpenByStockSymbol(symbol).map(p => Seq(p)).getOrElse(Seq.empty)

  override protected def upsertPosition(position: Position): Unit = ???

}
