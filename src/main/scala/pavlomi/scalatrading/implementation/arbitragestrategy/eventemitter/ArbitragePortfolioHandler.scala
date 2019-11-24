package pavlomi.scalatrading.implementation.arbitragestrategy.eventemitter
import pavlomi.scalatrading.domain.{EmptyEvent, Position, StockSymbol}
import pavlomi.scalatrading.eventemitter.{PortfolioHandler, PortfolioHandlerAsync}
import pavlomi.scalatrading.implementation.arbitragestrategy.domain.ArbitragePositionEvent

import scala.concurrent.Future

class ArbitragePortfolioHandler extends PortfolioHandlerAsync[ArbitragePositionEvent, EmptyEvent.type]{

  override def execute(event: ArbitragePositionEvent): Future[EmptyEvent.type] = ???

}
