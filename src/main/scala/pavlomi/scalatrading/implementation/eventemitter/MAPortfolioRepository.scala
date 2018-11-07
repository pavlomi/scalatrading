package pavlomi.scalatrading.implementation.eventemitter

import pavlomi.scalatrading.domain.{Position, PositionId, StockSymbol}
import pavlomi.scalatrading.eventemitter.PortfolioRepository
import pavlomi.scalatrading.implementation.domain.MAPosition

class MAPortfolioRepository extends PortfolioRepository {

  override def delete(positionId: PositionId): Unit             = ???
  override def find(positionId: PositionId): Option[MAPosition] = store.get(positionId)

  override def insert(position: Position): Unit =
    position match {
      case position: MAPosition => store + (position.uuid -> position)
      case _                    => throw new IllegalArgumentException
    }

  override def update(positionId: PositionId, position: Position): Unit = ???
//    position match {
//      case position: MAPosition => store(positionId) = position
//      case _                    => throw new IllegalArgumentException
//    }

  def findOpenByStockSymbol(stockSymbol: StockSymbol): Option[MAPosition] =
    store
      .find {
        case (_, position) => position.symbol == stockSymbol && position.close.isEmpty
      }
      .map(_._2)

  private val store = Map.empty[PositionId, MAPosition]
}
