package pavlomi.scalatrading.implementation.mastrategy.eventemitter
import java.util.UUID

import pavlomi.scalatrading.domain._
import pavlomi.scalatrading.eventemitter.{OrderHandler, OrderHttpProvider}
import pavlomi.scalatrading.implementation.mastrategy.domain._

class MAOrderHandler(MAOrderHttpProvider: MAOrderHttpProvider) extends OrderHandler[MAOrderEvent, MAPositionEvent] {

  override def execute(event: MAOrderEvent): MAPositionEvent =
    (event.direction, event.position) match {
      case (PositionDirection.Buy, None) =>
        val marketRequest = MarketRequest(event.symbol, event.price, event.value, PositionDirection.Buy)
        orderHttpProvider.sendOpenRequest(marketRequest)
        val stopLost = sentStopLossRequestIfExist(event)
        MAPositionEvent(event.price, event.symbol, event.direction, event.value, event.operationTime, stopLost)
      case (PositionDirection.Sell, None) =>
        val marketRequest = MarketRequest(event.symbol, event.price, event.value, PositionDirection.Sell)
        orderHttpProvider.sendOpenRequest(marketRequest)
        val stopLost = sentStopLossRequestIfExist(event)
        MAPositionEvent(event.price, event.symbol, event.direction, event.value, event.operationTime, stopLost)
      case (PositionDirection.Buy, Some(position)) if position.direction.isShort =>
        val marketRequest = MarketRequest(event.symbol, event.price, event.value, PositionDirection.Buy)
        orderHttpProvider.sendCloseRequest(marketRequest)
        val stopLost = sentStopLossRequestIfExist(event)
        MAPositionEvent(event.price, event.symbol, event.direction, event.value, event.operationTime, stopLost, Some(position))
      case (PositionDirection.Sell, Some(position)) if position.direction.isLong =>
        val marketRequest = MarketRequest(event.symbol, event.price, event.value, PositionDirection.Sell)
        orderHttpProvider.sendCloseRequest(marketRequest)
        val stopLost = sentStopLossRequestIfExist(event)
        MAPositionEvent(event.price, event.symbol, event.direction, event.value, event.operationTime, stopLost, Some(position))
    }

  private def sentStopLossRequestIfExist(event: MAOrderEvent): Option[MAStopLoss] =
    event.maStopLossDto.map { stopLossDto =>
      val limitRequest = LimitRequest(event.symbol, Price(stopLossDto.stop.value), event.value, event.direction.revert)
      orderHttpProvider.setStopLose(limitRequest)
      MAStopLoss(SLExternalNumber(UUID.randomUUID().toString), stopLossDto.limit, stopLossDto.stop, SLCount(event.value.value))
    }

  override def orderHttpProvider: OrderHttpProvider = MAOrderHttpProvider
}
