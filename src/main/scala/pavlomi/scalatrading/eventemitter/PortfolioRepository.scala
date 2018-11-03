package pavlomi.scalatrading.eventemitter

import pavlomi.scalatrading.domain.{Position, PositionId}

trait PortfolioRepository {
  def insert(position: Position): Unit

  def update(positionId: PositionId, position: Position): Unit

  def find(positionId: PositionId): Option[Position]

  def delete(positionId: PositionId): Unit
}
