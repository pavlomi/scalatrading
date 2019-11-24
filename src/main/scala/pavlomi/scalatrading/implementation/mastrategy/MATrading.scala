package pavlomi.scalatrading.implementation.mastrategy

import pavlomi.scalatrading.dataprovider.DataProvider
import pavlomi.scalatrading.implementation.mastrategy.domain.MADataEvent
import pavlomi.scalatrading.implementation.mastrategy.eventloop.MAEventLoop

class MATrading[E](
  dataProvider: DataProvider[E],
  maEventLoop: MAEventLoop
) {

  def run(): Unit = dataProvider.execute(candlestick => maEventLoop.execute(MADataEvent(candlestick)))

}
