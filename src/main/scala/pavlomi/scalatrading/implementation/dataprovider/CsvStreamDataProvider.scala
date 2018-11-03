package pavlomi.scalatrading.implementation.dataprovider

import akka.Done
import akka.stream.Materializer
import akka.stream.scaladsl.{Sink, Source}
import com.github.tototoshi.csv.CSVReader
import pavlomi.scalatrading.dataprovider.{DataFormatter, DataProvider}
import pavlomi.scalatrading.domain.{Candlestick, StockSymbol}

import scala.concurrent.Future

class CsvStreamDataProvider(val stockSymbol: StockSymbol, dataFormatter: DataFormatter[Map[String, String]])(implicit mac: Materializer)
    extends DataProvider[Map[String, String]] {

  override def execute(f: Candlestick => Future[Done]): Future[Done] = {
    val file = getClass.getClassLoader
      .getResource(CsvStreamDataProvider.PATH_TO_FOLDER + "/" + stockSymbol.value + ".csv")
      .getFile

    Source(CSVReader.open(file).iteratorWithHeaders.toList)
      .map(dataFormatter.execute(_, stockSymbol))
      .mapAsync(1)(f)
      .runWith(Sink.ignore)
  }
}

object CsvStreamDataProvider {
  val PATH_TO_FOLDER = "data"
}
