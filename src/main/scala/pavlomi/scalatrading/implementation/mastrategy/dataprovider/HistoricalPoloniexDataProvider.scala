package pavlomi.scalatrading.implementation.mastrategy.dataprovider
import java.time.Instant

import pavlomi.poloniex.PoloniexPublicApi
import pavlomi.poloniex.PoloniexPublicApi.Period
import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.publicapi.ReturnChartDataResponse
import pavlomi.scalatrading.dataprovider.{DataFormatter, DataProvider}
import pavlomi.scalatrading.domain.{Candlestick, StockSymbol}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class HistoricalPoloniexDataProvider(
  poloniexPublicApi: PoloniexPublicApi,
  from: Instant,
  to: Instant,
  val stockSymbol: StockSymbol,
  dataFormatter: DataFormatter[ReturnChartDataResponse]
) extends DataProvider[ReturnChartDataResponse] {
  val Seq(counter, base)   = stockSymbol.value.split("_").toSeq
  val poloniexCurrencyPair = PoloniexCurrencyPair.from(counter, base)

  lazy val data = {
    Await.result(poloniexPublicApi.returnChartData(poloniexCurrencyPair, from, to, Period.s1800), Duration.Inf) match {
      case Right(response) => response
      case Left(failure)   => throw new Exception(failure.error)
    }
  }

  override def execute(f: Candlestick => Unit): Unit = data.value.map(dataFormatter.execute(_, stockSymbol)).foreach(f)

}
