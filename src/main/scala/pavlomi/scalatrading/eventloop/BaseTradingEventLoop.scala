package pavlomi.scalatrading.eventloop

import akka.Done
import pavlomi.scalatrading.domain._
import pavlomi.scalatrading.eventemitter.{MarketHandler, Portfolio, RiskManager, Strategy}

import scala.concurrent.{ExecutionContext, Future}

class BaseTradingEventLoop(
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

  override def eventProcessing(event: Event): Seq[Event] =
    event match {
      case dataEvent: DataEvent                   => strategy.execute(dataEvent)
      case signalEvent: SignalEvent               => riskManager.execute(signalEvent)
      case marketEvent: MarketEvent               => marketHandler.execute(marketEvent)
      case positionEvent: PositionEvent           => portfolio.execute(positionEvent)
      case setStopLostEvent: SetStopLostEvent     => Seq.empty[Event]
      case setTakeProfitEvent: SetTakeProfitEvent => Seq.empty[Event]
      case EmptyEvent                             => Seq.empty[Event]
    }

}
