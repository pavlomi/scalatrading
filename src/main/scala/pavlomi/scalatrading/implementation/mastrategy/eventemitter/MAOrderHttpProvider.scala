package pavlomi.scalatrading.implementation.mastrategy.eventemitter
import pavlomi.scalatrading.domain.{LimitRequest, MarketRequest}
import pavlomi.scalatrading.eventemitter.OrderHttpProvider

class MAOrderHttpProvider extends OrderHttpProvider {
  override def sendCloseRequest(marketRequest: MarketRequest): Unit = Unit
  override def sendOpenRequest(marketRequest: MarketRequest): Unit  = Unit
  override def setStopLose(marketRequest: LimitRequest): Unit       = Unit
  override def setTakeProfit(marketRequest: LimitRequest): Unit     = Unit
}
