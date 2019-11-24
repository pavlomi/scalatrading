package pavlomi.scalatrading.implementation.mastrategy.domain
import java.time.Instant

import pavlomi.scalatrading.domain._
import pavlomi.scalatrading.implementation.mastrategy.domain.dto.MAStopLossDto

case class MADataEvent(candlestick: Candlestick) extends DataEvent

case class MASignalEvent(
  symbol: StockSymbol,
  direction: PositionDirection,
  price: Price,
  operationTime: Instant,
  maStopLossDto: Option[MAStopLossDto] = None,
  position: Option[MAPosition] = None
) extends SignalEvent

case class MAOrderEvent(
  price: Price,
  symbol: StockSymbol,
  direction: PositionDirection,
  value: Value,
  operationTime: Instant,
  maStopLossDto: Option[MAStopLossDto],
  position: Option[MAPosition] = None
) extends OrderEvent

case class MAPositionEvent(
  price: Price,
  symbol: StockSymbol,
  direction: PositionDirection,
  value: Value,
  operationTime: Instant,
  maStopLoss: Option[MAStopLoss],
  position: Option[MAPosition] = None
) extends PositionEvent
