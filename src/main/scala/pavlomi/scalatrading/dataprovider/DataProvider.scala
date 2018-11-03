package pavlomi.scalatrading.dataprovider

import akka.Done
import pavlomi.scalatrading.domain.{Candlestick, StockSymbol}

import scala.concurrent.Future

abstract class DataProvider[I] {
  def stockSymbol: StockSymbol

  def execute(f: Candlestick => Future[Done]): Future[Done]
}

