package pavlomi.scalatrading.implementation.eventemitter
import pavlomi.scalatrading.eventemitter.{OrderHandler, OrderHttpProvider}
import pavlomi.scalatrading.implementation.domain.{MAOrderEvent, MAPositionEvent}

class MAOrderHandler(MAOrderHttpProvider: MAOrderHttpProvider) extends OrderHandler[MAOrderEvent, MAPositionEvent] {

  override def execute(event: MAOrderEvent): MAPositionEvent = {}

  override def orderHttpProvider: OrderHttpProvider = MAOrderHttpProvider
}
