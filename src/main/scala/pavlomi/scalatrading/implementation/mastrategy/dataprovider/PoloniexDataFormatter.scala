package pavlomi.scalatrading.implementation.mastrategy.dataprovider
import java.time.Instant

import pavlomi.poloniex.domain.dto.publicapi.ReturnChartDataResponse
import pavlomi.scalatrading.dataprovider.DataFormatter
import pavlomi.scalatrading.domain.{Candlestick, Price, StockSymbol, Value}

class PoloniexDataFormatter extends DataFormatter[ReturnChartDataResponse]{

  override def execute(
    data: ReturnChartDataResponse,
    stockSymbol: StockSymbol
  ): Candlestick = {

    val instant = Instant.ofEpochSecond(data.date.value)

    Candlestick(
      stockSymbol,
      Price(data.open.value),
      Price(data.close.value),
      Price(data.high.value),
      Price(data.low.value),
      Value(data.volume),
      instant
    )
  }

}
