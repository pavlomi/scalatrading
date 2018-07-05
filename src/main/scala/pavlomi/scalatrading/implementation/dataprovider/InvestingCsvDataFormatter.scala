package pavlomi.scalatrading.implementation.dataprovider

import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.time.{Instant, LocalDateTime, ZoneOffset}

import pavlomi.scalatrading.dataprovider.DataFormatter
import pavlomi.scalatrading.domain._

class InvestingCsvDataFormatter extends DataFormatter[Map[String, String]] {

  val dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
    .appendPattern("dd.MM.yyyy")
    .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
    .toFormatter

  override def execute(data: Map[String, String], stockSymbol: StockSymbol): Candlestick = {
    val instant: Instant = LocalDateTime.parse("04.05.2018", dateTimeFormatterBuilder).toInstant(ZoneOffset.UTC)
    CandlestickImpl(
      stockSymbol,
      Price(BigDecimal(data("Откр.").replaceAll(",", "."))),
      Price(BigDecimal(data("Цена").replaceAll(",", "."))),
      Price(BigDecimal(data("Макс.").replaceAll(",", "."))),
      Price(BigDecimal(data("Мин.").replaceAll(",", "."))),
      Value(BigDecimal(0)),
      instant
    )
  }
}
