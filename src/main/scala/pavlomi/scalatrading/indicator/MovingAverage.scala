package pavlomi.scalatrading.indicator

import pavlomi.scalatrading.domain.Candlestick

import scala.annotation.tailrec

trait MovingAverage {
  def items: Seq[Candlestick]
  def interval: Int

  /**
   * Return some value otherwise -1.
   */
  def calculate: BigDecimal

  def f: Candlestick => BigDecimal = item => item.close.value

  def prev: SMA                           = SMA(items.tail, interval)
  def next(candlestick: Candlestick): SMA = SMA(candlestick +: items, interval)
}

case class SMA(items: Seq[Candlestick], interval: Int) extends MovingAverage {
  require(interval > 0)
  require(items.nonEmpty)

  lazy val calculate: BigDecimal = {
    @tailrec
    def loop(i: Int, items: Seq[Candlestick], acc: BigDecimal): BigDecimal =
      if (i == 0) acc
      else if (items.isEmpty) BigDecimal(0)
      else loop(i - 1, items.tail, acc + f(items.head))

    if (items.size >= interval) {
      val sum = loop(interval, items, 0)

      if (sum > 0) sum / interval else sum
    } else -1
  }
}

case class EMA(items: Seq[Candlestick], interval: Int) extends MovingAverage {
  lazy val calculate: BigDecimal = ???
}
