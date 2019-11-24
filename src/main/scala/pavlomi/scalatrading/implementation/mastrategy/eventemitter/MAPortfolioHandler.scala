package pavlomi.scalatrading.implementation.mastrategy.eventemitter

import pavlomi.scalatrading.domain._
import pavlomi.scalatrading.eventemitter.PortfolioHandler
import pavlomi.scalatrading.implementation.mastrategy.domain.{MAPosition, MAPositionEvent}

class MAPortfolioHandler(maPortfolioRepository: MAPortfolioRepository) extends PortfolioHandler[MAPositionEvent, EmptyEvent.type] {

  override def execute(event: MAPositionEvent): EmptyEvent.type = {
    (event.position, event.maStopLoss) match {
      case (Some(position), _) =>
        val updatedPosition = position.copy(close = Some(event.price), closeInstant = Some(event.operationTime))
        maPortfolioRepository.update(position.uuid, updatedPosition)
      case (None, Some(stopLoss)) =>
        val position =
          MAPosition(PositionId.generate, event.price, event.operationTime, None, None, event.value, event.symbol, event.direction, Seq.empty, stopLoss)
        maPortfolioRepository.insert(position)
      case (None, _) => throw new Exception("Position without stop loss.")
    }

    EmptyEvent
  }

  override def getOpenPositions(symbol: StockSymbol): Seq[MAPosition] = maPortfolioRepository.findOpenByStockSymbol(symbol).toSeq

  override protected def upsertPosition(position: Position): Unit = ???

  def calculateResult(): Unit = {

    val positions = maPortfolioRepository.store.toSeq
      .map(_._2)
      .sortBy(_.openInstant)
      .filter(_.direction == PositionDirection.Buy)
      .filterNot(_.diff == MAPosition.INVALID_CALCULATION)

    val income = positions
      .foldLeft(BigDecimal(1000)) {
        case (acc, position) =>
          if (position.diff == MAPosition.INVALID_CALCULATION) acc
          else {

//            println(
//              position.open,
//              position.direction,
//              position.close,
//              position.stopLoss.stop,
//              position.calculateClosePrice,
//              position.diff,
//              position.calculateClosePrice == position.stopLoss.stop.value
//            )
//            println()
//            println()
            acc + position.income
          }
      }

    println("INCOME", income)

    val (incomePositions, lossPositions) = positions.partition(_.diff >= 0)
    println("Income/loss", incomePositions.size, lossPositions.size)

  }
}
