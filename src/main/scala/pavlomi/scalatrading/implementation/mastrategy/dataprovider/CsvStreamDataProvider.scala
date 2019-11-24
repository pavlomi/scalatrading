package pavlomi.scalatrading.implementation.mastrategy.dataprovider

import com.github.tototoshi.csv.CSVReader
import pavlomi.scalatrading.dataprovider.{DataFormatter, DataProvider}
import pavlomi.scalatrading.domain.{Candlestick, StockSymbol}

class CsvStreamDataProvider(val stockSymbol: StockSymbol, dataFormatter: DataFormatter[Map[String, String]]) extends DataProvider[Map[String, String]] {

  override def execute(f: Candlestick => Unit): Unit = {
    val file = getClass.getClassLoader
      .getResource(CsvStreamDataProvider.PATH_TO_FOLDER + "/" + stockSymbol.value + ".csv")
      .getFile
    CSVReader
      .open(file)
      .iteratorWithHeaders
      .map(dataFormatter.execute(_, stockSymbol))
      .foreach(f)
  }
}

object CsvStreamDataProvider {
  val PATH_TO_FOLDER = "data"
}
