package pavlomi.scalatrading.implementation.arbitragestrategy.eventemitter
import pavlomi.scalatrading.domain.Value
import pavlomi.scalatrading.eventemitter.RiskManagerHandlerAsync
import pavlomi.scalatrading.implementation.arbitragestrategy.domain.{ArbitrageOrderEvent, ArbitrageSignalEvent}

import scala.concurrent.Future

class ArbitrageRiskManagerHandler(fund: BigDecimal) extends RiskManagerHandlerAsync[ArbitrageSignalEvent, ArbitrageOrderEvent] {
  override def execute(event: ArbitrageSignalEvent): Future[ArbitrageOrderEvent] =
    Future.successful {
      ArbitrageOrderEvent(event.symbol, event.direction, event.price, Value(event.value.value * fund), event.number, event.step)
    }

}
