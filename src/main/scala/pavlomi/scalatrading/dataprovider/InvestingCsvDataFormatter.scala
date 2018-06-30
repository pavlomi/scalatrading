package pavlomi.scalatrading.dataprovider

import pavlomi.scalatrading.domain.Candlestick

class InvestingCsvDataFormatter extends DataFormatter[Map[String, String]] {
  override def execute(data: Map[String, String]): Candlestick = ???
}
