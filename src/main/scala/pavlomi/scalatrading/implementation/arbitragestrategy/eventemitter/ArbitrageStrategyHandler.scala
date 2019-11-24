package pavlomi.scalatrading.implementation.arbitragestrategy.eventemitter
import java.util.UUID

import pavlomi.poloniex.PoloniexPublicApi
import pavlomi.poloniex.domain.dto.publicapi.{ReturnTicket, ReturnTicketResponse}
import pavlomi.scalatrading.domain._
import pavlomi.scalatrading.eventemitter.StrategyAsync
import pavlomi.scalatrading.implementation.arbitragestrategy.ArbitrageStrategy
import pavlomi.scalatrading.implementation.arbitragestrategy.ArbitrageStrategy.{Arbitrage3Step, CalculatedArbitrage3Step, CalculatedStep}
import pavlomi.scalatrading.implementation.arbitragestrategy.domain.{ArbitrageDataEvent, ArbitrageSignalEvent}

import scala.concurrent.{ExecutionContext, Future}

class ArbitrageStrategyHandler(poloniexPublicApi: PoloniexPublicApi)(implicit ec: ExecutionContext) extends StrategyAsync[ArbitrageDataEvent, Event] {

  override def execute(event: ArbitrageDataEvent): Future[Event] =
    poloniexPublicApi.returnTicket().map {
      case Right(value) =>
        calculate(event.arbitrage3Step, value) match {
          case Some(calculatedArbitrage3Step) if calculatedArbitrage3Step.isPositiveWithFee(RISK_LEVEL_PRC) =>
            val uuid           = UUID.randomUUID()
            val arbitrageStep1 = toEvent(calculatedArbitrage3Step.calculatedStepFirst, uuid, ARBITRAGE_FIRST_STEP_NUMBER)
            val arbitrageStep2 = toEvent(calculatedArbitrage3Step.calculatedStepSecond, uuid, ARBITRAGE_SECOND_STEP_NUMBER)
            val arbitrageStep3 = toEvent(calculatedArbitrage3Step.calculatedStepThird, uuid, ARBITRAGE_THIRD_STEP_NUMBER)

            arbitrageStep1 + arbitrageStep2 + arbitrageStep3
          case Some(_) => EmptyEvent
          case None    => EmptyEvent
        }
      case Left(_) => EmptyEvent
    }

  private def toEvent(calculatedStep: CalculatedStep, uuid: UUID, stepNumber: Int) =
    ArbitrageSignalEvent(
      StockSymbol(calculatedStep.arbitrageStep.poloniexCurrencyPair.toString),
      calculatedStep.arbitrageStep.positionDirection,
      Price(calculatedStep.price),
      Value(calculatedStep.countWithFee),
      uuid,
      stepNumber
    )

  def calculate(arbitrage3Step: Arbitrage3Step, returnTicketResponse: ReturnTicketResponse) = {
    val Arbitrage3Step(firstStep, secondStep, thirdStep) = arbitrage3Step
    val fee                                              = BigDecimal(0.2)
    for {
      first <- returnTicketResponse.value.get(firstStep.poloniexCurrencyPair)
      firstPrice          = ArbitrageStrategy.getPrice(firstStep.positionDirection, first)
      firstStepCalculated = CalculatedStep.calculate(firstStep, firstPrice, fee, CALCULATION_FUND)
      second <- returnTicketResponse.value.get(secondStep.poloniexCurrencyPair)
      secondPrice          = ArbitrageStrategy.getPrice(secondStep.positionDirection, second)
      secondStepCalculated = CalculatedStep.calculate(secondStep, secondPrice, fee, firstStepCalculated.count)
      third <- returnTicketResponse.value.get(thirdStep.poloniexCurrencyPair)
      thirdPrice          = ArbitrageStrategy.getPrice(thirdStep.positionDirection, third)
      thirdStepCalculated = CalculatedStep.calculate(thirdStep, thirdPrice, fee, secondStepCalculated.count)
    } yield CalculatedArbitrage3Step(firstStepCalculated, secondStepCalculated, thirdStepCalculated)
  }

  private val ARBITRAGE_FIRST_STEP_NUMBER  = 1
  private val ARBITRAGE_SECOND_STEP_NUMBER = 2
  private val ARBITRAGE_THIRD_STEP_NUMBER  = 3

  private val CALCULATION_FUND = BigDecimal(1)
//  private val SCALE_NUMBER     = 8
  private val RISK_LEVEL_PRC = BigDecimal(0.00001)
//  private val ROUNDING_MODE    = BigDecimal.RoundingMode.HALF_DOWN
}
