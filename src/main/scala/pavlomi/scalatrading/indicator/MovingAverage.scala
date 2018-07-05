package pavlomi.scalatrading.indicator

import pavlomi.scalatrading.domain.Candlestick

import scala.annotation.tailrec

trait MovingAverage {
  def calculate(items: Seq[Candlestick], interval: Int): BigDecimal
}

object SMA extends MovingAverage {
  override def calculate(items: Seq[Candlestick], interval: Int): BigDecimal = {
    @tailrec
    def loop(i: Int, items: Seq[Candlestick], acc: BigDecimal): BigDecimal =
      if (i == 0) acc
      else if (items.isEmpty) BigDecimal(0)
      else loop(i - 1, items.tail, acc + items.head.close.value)

    val sum = loop(interval, items, 0)

    if (sum > 0) sum / interval else sum
  }
}

object EMA extends MovingAverage {
  override def calculate(items: Seq[Candlestick], interval: Int): BigDecimal = ???
}
