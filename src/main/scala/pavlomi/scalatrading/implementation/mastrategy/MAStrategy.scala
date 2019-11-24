package pavlomi.scalatrading.implementation.mastrategy

import java.time.Instant

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import pavlomi.poloniex.PoloniexPublicApi
import pavlomi.scalatrading.domain.StockSymbol
import pavlomi.scalatrading.implementation.mastrategy.dataprovider._
import pavlomi.scalatrading.implementation.mastrategy.eventemitter._
import pavlomi.scalatrading.implementation.mastrategy.eventloop.MAEventLoop

import scala.concurrent.{Await, ExecutionContext}

class MAStrategy(implicit as: ActorSystem, mat: ActorMaterializer, ex: ExecutionContext) {
  def run(): Unit = {
    val poloniexPublicApi = new PoloniexPublicApi

    val start         = Instant.parse(s"2016-06-01T00:00:00Z")
    val end           = Instant.parse(s"2019-01-01T00:00:00Z")
    val dataFormatter = new PoloniexDataFormatter
    val dataProvider  = new HistoricalPoloniexDataProvider(poloniexPublicApi, start, end, StockSymbol("USDT_BTC"), dataFormatter)

    val managerHandlerHandler = new MARiskManagerHandlerHandler()
    val MAOrderHttpProvider   = new MAOrderHttpProvider()
    val MAOrderHandler        = new MAOrderHandler(MAOrderHttpProvider)
    val maPortfolioRepository = new MAPortfolioRepository()
    val maPortfolioHandler    = new MAPortfolioHandler(maPortfolioRepository)
    val maStrategy            = new MAStrategyHandler(portfolioRepository = maPortfolioRepository)
    val maEventLoop           = new MAEventLoop(maStrategy, managerHandlerHandler, MAOrderHandler, maPortfolioHandler)
    val maTrading             = new MATrading(dataProvider, maEventLoop)

    maTrading.run()

    maPortfolioHandler.calculateResult()
  }
}
