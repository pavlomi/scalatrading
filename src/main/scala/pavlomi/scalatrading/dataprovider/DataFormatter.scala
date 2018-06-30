package pavlomi.scalatrading.dataprovider

import pavlomi.scalatrading.domain.Candlestick

trait DataFormatter[T] {
  def execute(data: T): Candlestick
}
