package pavlomi.scalatrading.implementation.arbitragestrategy.eventloop
import pavlomi.scalatrading.domain.{EmptyEvent, Event}
import pavlomi.scalatrading.eventloop.{AsyncEventLoop, AsyncFifoEventLoop}
import pavlomi.scalatrading.implementation.arbitragestrategy.domain.{ArbitrageDataEvent, ArbitrageOrderEvent, ArbitragePositionEvent, ArbitrageSignalEvent}
import pavlomi.scalatrading.implementation.arbitragestrategy.eventemitter.{ArbitrageOrderHandler, ArbitragePortfolioHandler, ArbitrageRiskManagerHandler, ArbitrageStrategyHandler}

import scala.concurrent.{ExecutionContext, Future}

class ArbitrageEventLoop(
  strategyHandler: ArbitrageStrategyHandler,
  riskManagerHandler: ArbitrageRiskManagerHandler,
  orderHandler: ArbitrageOrderHandler,
  portfolioHandler: ArbitragePortfolioHandler
)(implicit val ec: ExecutionContext) extends AsyncEventLoop
    with AsyncFifoEventLoop {

  override def execute(dataEvent: Event): Unit = {
    addEvent(dataEvent)
    run()
  }

  override protected def eventProcessing(event: Event): Future[Event] = ???
//  override protected def eventProcessing(event: Event): Event =
//    event match {
//      case dataEvent: ArbitrageDataEvent.type     => strategyHandler.execute(dataEvent)
//      case signalEvent: ArbitrageSignalEvent      => riskManagerHandler.execute(signalEvent)
//      case orderEvent: ArbitrageOrderEvent        => orderHandler.execute(orderEvent)
//      case portfolioEvent: ArbitragePositionEvent => portfolioHandler.execute(portfolioEvent)
//      case _                                      => EmptyEvent
//    }

}
