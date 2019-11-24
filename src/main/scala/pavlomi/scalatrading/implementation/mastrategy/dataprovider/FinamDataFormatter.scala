package pavlomi.scalatrading.implementation.mastrategy.dataprovider
import java.time.{Instant, LocalDateTime, ZoneOffset}
import java.time.format.DateTimeFormatter

import pavlomi.scalatrading.dataprovider.DataFormatter
import pavlomi.scalatrading.domain.{Candlestick, Price, StockSymbol, Value}

class FinamDataFormatter extends DataFormatter[Map[String, String]]{

  val dateTimeFormatterBuilder = DateTimeFormatter.ofPattern("yyyyMMdd HHmm00")

  override def execute(data: Map[String, String], stockSymbol: StockSymbol): Candlestick = {
    val instant: Instant = LocalDateTime.parse(data("<DATE>") + " " + data("<TIME>"), dateTimeFormatterBuilder).toInstant(ZoneOffset.UTC)
    Candlestick(
      stockSymbol,
      Price(BigDecimal(data("<OPEN>").replaceAll(",", "."))),
      Price(BigDecimal(data("<CLOSE>").replaceAll(",", "."))),
      Price(BigDecimal(data("<HIGH>").replaceAll(",", "."))),
      Price(BigDecimal(data("<LOW>").replaceAll(",", "."))),
      Value(BigDecimal(data("<VOL>").replaceAll(",", "."))),
      instant
    )
  }
}
