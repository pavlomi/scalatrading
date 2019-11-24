package pavlomi.scalatrading.dataprovider

import pavlomi.scalatrading.domain.{Candlestick, StockSymbol}

abstract class DataProvider[I] {
  def stockSymbol: StockSymbol

  def execute(f: Candlestick => Unit): Unit
}
