package  pavlomi.scalatrading
import akka.Done
import pavlomi.scalatrading.dataprovider.DataProvider
import pavlomi.scalatrading.domain.DataEvent
import pavlomi.scalatrading.eventloop.TradingEventLoop

import scala.concurrent.{ExecutionContext, Future}

class Trading(dataProvider: DataProvider[Map[String, String]], openStrategyLoop: TradingEventLoop)(implicit ex: ExecutionContext) {
  def run(): Future[Done] =
    dataProvider.execute(candlestick => openStrategyLoop.execute(DataEvent(candlestick, None)))
}
