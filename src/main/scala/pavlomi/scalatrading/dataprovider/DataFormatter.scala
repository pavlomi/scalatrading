package pavlomi.scalatrading.dataprovider

import pavlomi.scalatrading.domain.{Candlestick, StockSymbol}

trait DataFormatter[T] {
  def execute(data: T, stockSymbol: StockSymbol): Candlestick
}
