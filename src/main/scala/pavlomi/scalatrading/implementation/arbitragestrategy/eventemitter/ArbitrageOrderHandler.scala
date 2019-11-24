package pavlomi.scalatrading.implementation.arbitragestrategy.eventemitter
import pavlomi.poloniex.PoloniexTradingApi
import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.tradingapi.{BuySuccessResponse, SellSuccessResponse}
import pavlomi.scalatrading.domain.{EmptyEvent, Event, PositionDirection}
import pavlomi.scalatrading.eventemitter.{OrderHandlerAsync, OrderHttpProvider}
import pavlomi.scalatrading.implementation.arbitragestrategy.domain.ArbitrageOrderEvent

import scala.concurrent.{ExecutionContext, Future}

class ArbitrageOrderHandler(
  val orderHttpProvider: OrderHttpProvider,
  poloniexTradingApi: PoloniexTradingApi
)(implicit ec: ExecutionContext)
    extends OrderHandlerAsync[ArbitrageOrderEvent, Event] {

  override def execute(event: ArbitrageOrderEvent): Future[Event] = {

    val operation = event.direction match {
      case PositionDirection.Buy  => poloniexTradingApi.buy(_, _, _)
      case PositionDirection.Sell => poloniexTradingApi.sell(_, _, _)
    }

    val elements             = event.symbol.value.split("_")
    val poloniexCurrencyPair = PoloniexCurrencyPair.from(elements(0), elements(1))

    operation(poloniexCurrencyPair, event.price.value, event.value.value).flatMap {
      case Right(value) =>
        val (orderNumber, resultingTrade) = value match {
          case b: BuySuccessResponse  => (b.orderNumber, b.resultingTrades)
          case s: SellSuccessResponse => (s.orderNumber, s.resultingTrades)
          case error                  => throw new Exception(error.toString)
        }

        resultingTrade match {
          case _ :: _ => Future.successful(EmptyEvent)
          case Nil    => poloniexTradingApi.cancelOrder(orderNumber).map(_ => EmptyEvent)
        }
      case Left(value) => Future.successful(EmptyEvent)
    }
  }
}
