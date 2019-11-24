package pavlomi.scalatrading.implementation.mastrategy.eventemitter

import pavlomi.scalatrading.domain.Value
import pavlomi.scalatrading.eventemitter.RiskManagerHandler
import pavlomi.scalatrading.implementation.mastrategy.domain.{MAOrderEvent, MASignalEvent}

class MARiskManagerHandlerHandler(initCount: BigDecimal = 1000, riskPerPosition: BigDecimal = 50) extends RiskManagerHandler[MASignalEvent, MAOrderEvent] {
  override def execute(event: MASignalEvent): MAOrderEvent =
    event.position match {
      case Some(position) =>
        MAOrderEvent(event.price, event.symbol, event.direction, position.value, event.operationTime, event.maStopLossDto, event.position)
      case None =>
        val value = Value(riskSavings / event.price.value)
        MAOrderEvent(event.price, event.symbol, event.direction, value, event.operationTime, event.maStopLossDto, None)
    }

  private def riskSavings = initCount * riskPerPosition / 100
}
