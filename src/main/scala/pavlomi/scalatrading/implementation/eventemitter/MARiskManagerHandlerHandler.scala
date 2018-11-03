package pavlomi.scalatrading.implementation.eventemitter

import pavlomi.scalatrading.domain.Value
import pavlomi.scalatrading.eventemitter.RiskManagerHandler
import pavlomi.scalatrading.implementation.domain.{MAOrderEvent, MASignalEvent}

class MARiskManagerHandlerHandler(initCount: BigDecimal = 1000, riskPerPosition: BigDecimal = 2) extends RiskManagerHandler[MASignalEvent, MAOrderEvent] {
  override def execute(event: MASignalEvent): MAOrderEvent =
    event.position match {
      case Some(position) =>
        MAOrderEvent(event.price, event.symbol, event.direction, position.value, event.position)
      case None =>
        val value = Value(riskSavings / event.price.value)
        MAOrderEvent(event.price, event.symbol, event.direction, value, None)
    }

  private def riskSavings = initCount * riskPerPosition / 100
}
