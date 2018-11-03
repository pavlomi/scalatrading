package pavlomi.scalatrading.eventemitter
import pavlomi.scalatrading.domain.MarketRequest

trait OrderHttpProvider {
  def sendOpenRequest(marketRequest: MarketRequest): Unit
  def sendCloseRequest(marketRequest: MarketRequest): Unit
  def setStopLose(marketRequest: MarketRequest): Unit
  def setTakeProfit(marketRequest: MarketRequest): Unit
}
