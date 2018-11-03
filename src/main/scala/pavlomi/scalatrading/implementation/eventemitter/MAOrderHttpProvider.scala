package pavlomi.scalatrading.implementation.eventemitter
import pavlomi.scalatrading.domain.MarketRequest
import pavlomi.scalatrading.eventemitter.OrderHttpProvider

class MAOrderHttpProvider extends OrderHttpProvider {
  override def sendCloseRequest(marketRequest: MarketRequest): Unit = ???
  override def sendOpenRequest(marketRequest: MarketRequest): Unit  = ???
  override def setStopLose(marketRequest: MarketRequest): Unit      = ???
  override def setTakeProfit(marketRequest: MarketRequest): Unit    = ???
}
