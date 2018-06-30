package pavlomi.scalatrading.dataprovider

import akka.Done
import akka.stream.Materializer
import akka.stream.scaladsl.{Sink, Source}
import com.github.tototoshi.csv.CSVReader
import pavlomi.scalatrading.domain.{Candlestick, StockSymbol}

import scala.concurrent.Future

class CsvStreamDataProvider(stockSymbol: StockSymbol, dataFormatter: DataFormatter[Map[String, String]])(implicit mac: Materializer)
    extends DataProvider[Map[String, String]](stockSymbol) {
  override def execute(f: Candlestick => Future[Done]): Future[Done] = {
    val file = getClass.getClassLoader
      .getResource(
        CsvStreamDataProvider.PATH_TO_FOLDER + "/" + stockSymbol.value + ".csv")
      .getFile

    Source
      .fromIterator[Map[String, String]](() =>
        CSVReader.open(file).iteratorWithHeaders)
      .map(dataFormatter.execute(_))
      .mapAsync(1)(f)
      .runWith(Sink.ignore)
  }
}

object CsvStreamDataProvider {
  val PATH_TO_FOLDER = "data"
}
