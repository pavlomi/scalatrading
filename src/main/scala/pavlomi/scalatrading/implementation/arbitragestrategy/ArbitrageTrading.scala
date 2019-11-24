package pavlomi.scalatrading.implementation.arbitragestrategy
import pavlomi.scalatrading.implementation.arbitragestrategy.domain.ArbitrageDataEvent
import pavlomi.scalatrading.implementation.arbitragestrategy.eventloop.ArbitrageEventLoop

class ArbitrageTrading(
  arbitrageEventLoop: ArbitrageEventLoop
) {

  def run(): Unit =
    while (true) {
//      arbitrageEventLoop.execute(ArbitrageDataEvent)
    }
}
