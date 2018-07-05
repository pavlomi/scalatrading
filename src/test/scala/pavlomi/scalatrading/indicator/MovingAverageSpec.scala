package pavlomi.scalatrading.indicator

import org.scalatest.FlatSpec
import pavlomi.scalatrading.utils.factory.CandlestickFactory

class MovingAverageSpec extends FlatSpec {

  behavior of "Simple Moving Average"

  it must "return moving average" in {
    val s1 = CandlestickFactory.create()
    val s2 = CandlestickFactory.create()
    val s3 = CandlestickFactory.create()

    val slist = Seq(s1, s2, s3)
    val sma   = slist.map(_.close.value).sum / slist.size

    assert(SMA.calculate(slist, 3) == sma)

    assert(SMA.calculate(slist, 4) == 0)
  }

}
