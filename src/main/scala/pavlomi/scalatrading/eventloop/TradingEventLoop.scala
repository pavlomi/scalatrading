package  pavlomi.scalatrading.eventloop

import pavlomi.scalatrading.eventemitter._

import scala.concurrent.ExecutionContext

abstract class TradingEventLoop(implicit ec: ExecutionContext) extends EventLoop {
  val strategy: Strategy
  val riskManager: RiskManager
  val marketHandler: MarketHandler
  val portfolio: Portfolio
}
