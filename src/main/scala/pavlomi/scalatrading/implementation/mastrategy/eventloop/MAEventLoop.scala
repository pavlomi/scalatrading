package pavlomi.scalatrading.implementation.mastrategy.eventloop

import pavlomi.scalatrading.domain.{EmptyEvent, Event}
import pavlomi.scalatrading.eventemitter.EventLoop
import pavlomi.scalatrading.eventloop.FifoEventLoop
import pavlomi.scalatrading.implementation.mastrategy.domain.{MADataEvent, MAOrderEvent, MAPositionEvent, MASignalEvent}
import pavlomi.scalatrading.implementation.mastrategy.eventemitter.{MAOrderHandler, MAPortfolioHandler, MARiskManagerHandlerHandler, MAStrategyHandler}

class MAEventLoop(
  strategy: MAStrategyHandler,
  managerHandlerHandler: MARiskManagerHandlerHandler,
  orderHandler: MAOrderHandler,
  positionHandler: MAPortfolioHandler
) extends EventLoop
    with FifoEventLoop {

  def execute(event: Event): Unit = {
    addEvent(event)
    run()
  }

  override protected def eventProcessing(event: Event): Event =
    event match {
      case dataEvent: MADataEvent         => strategy.execute(dataEvent)
      case signalEvent: MASignalEvent     => managerHandlerHandler.execute(signalEvent)
      case orderEvent: MAOrderEvent       => orderHandler.execute(orderEvent)
      case positionEvent: MAPositionEvent => positionHandler.execute(positionEvent)
      case _                              => EmptyEvent
    }
}
