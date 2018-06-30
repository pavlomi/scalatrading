package pavlomi.scalatrading.implementation

import akka.Done
import pavlomi.scalatrading.domain._
import pavlomi.scalatrading.eventemitter.{MarketHandler, Portfolio, RiskManager, Strategy}
import pavlomi.scalatrading.eventloop.{FifoEventLoop, TradingEventLoop}

import scala.concurrent.{ExecutionContext, Future}

class TradingEventLoopImpl(
  override val marketHandler: MarketHandler,
  override val strategy: Strategy,
  override val riskManager: RiskManager,
  override val portfolio: Portfolio
)(implicit ec: ExecutionContext)
    extends TradingEventLoop
    with FifoEventLoop {

  override def execute(dataEvent: DataEvent): Future[Done] = {
    val dataEventWithPosition = portfolio
      .getOpenPositions(dataEvent.candlestick.symbol)
      .map(p => dataEvent.copy(position = Some(p)))

    if (dataEventWithPosition.nonEmpty) dataEventWithPosition.map(addEvent)
    else addEvent(dataEvent)

    run()
  }

  override def eventProcessing(event: Event): Option[Event] =
    event match {
      case dataEvent: DataEvent         => strategy.execute(dataEvent)
      case openSignalEvent: SignalEvent => riskManager.execute(openSignalEvent)
      case marketEvent: MarketEvent     => marketHandler.execute(marketEvent)
      case positionEvent: PositionEvent => portfolio.execute(positionEvent)
      case EmptyEvent                   => None
    }

}
