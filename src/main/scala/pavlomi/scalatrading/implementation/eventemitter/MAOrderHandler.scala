package pavlomi.scalatrading.implementation.eventemitter
import pavlomi.scalatrading.domain.{MarketRequest, PositionDirection}
import pavlomi.scalatrading.eventemitter.{OrderHandler, OrderHttpProvider}
import pavlomi.scalatrading.implementation.domain.{MAOrderEvent, MAPositionEvent}

class MAOrderHandler(MAOrderHttpProvider: MAOrderHttpProvider) extends OrderHandler[MAOrderEvent, MAPositionEvent] {

  override def execute(event: MAOrderEvent): MAPositionEvent =
    (event.direction, event.position) match {
      case (PositionDirection.Buy, None) =>
        val marketRequest = MarketRequest("Market", event.symbol, event.price, PositionDirection.Buy)
        orderHttpProvider.sendOpenRequest(marketRequest)
        MAPositionEvent(event.price, event.symbol, event.direction, event.value)
      case (PositionDirection.Sell, None) =>
        val marketRequest = MarketRequest("Market", event.symbol, event.price, PositionDirection.Sell)
        orderHttpProvider.sendOpenRequest(marketRequest)
        MAPositionEvent(event.price, event.symbol, event.direction, event.value)
      case (PositionDirection.Buy, Some(position)) if position.direction.isShort =>
        val marketRequest = MarketRequest("Market", event.symbol, event.price, PositionDirection.Buy)
        orderHttpProvider.sendCloseRequest(marketRequest)
        MAPositionEvent(event.price, event.symbol, event.direction, event.value)
      case (PositionDirection.Sell, Some(position)) if position.direction.isLong =>
        val marketRequest = MarketRequest("Market", event.symbol, event.price, PositionDirection.Sell)
        orderHttpProvider.sendCloseRequest(marketRequest)
        MAPositionEvent(event.price, event.symbol, event.direction, event.value)
    }

  override def orderHttpProvider: OrderHttpProvider = MAOrderHttpProvider
}
