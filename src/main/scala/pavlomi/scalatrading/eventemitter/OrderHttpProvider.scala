package pavlomi.scalatrading.eventemitter
import pavlomi.scalatrading.domain.{LimitRequest, MarketRequest}

trait OrderHttpProvider {
  def sendOpenRequest(marketRequest: MarketRequest): Unit
  def sendCloseRequest(marketRequest: MarketRequest): Unit
  def setStopLose(marketRequest: LimitRequest): Unit
  def setTakeProfit(marketRequest: LimitRequest): Unit
}
