package pavlomi.scalatrading.implementation.arbitragestrategy
import java.time.Instant

import akka.Done
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import pavlomi.poloniex.{PoloniexPublicApi, PoloniexTradingApi}
import pavlomi.poloniex.domain.{OrderNumber, PoloniexAPIKey, PoloniexCurrencyPair, PoloniexSecret}
import pavlomi.poloniex.domain.dto.PoloniexErrorResponse
import pavlomi.poloniex.domain.dto.publicapi.{ReturnTicket, ReturnTicketResponse}
import pavlomi.poloniex.domain.dto.tradingapi.{BuyErrorResponse, BuySuccessResponse, ResultingTrade, ReturnOrderTrades, SellErrorResponse, SellSuccessResponse}
import pavlomi.scalatrading.domain.PositionDirection

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

// USDT -> BTC -> ETH,ETC,XMR,ZEC,LTC,XRP -> USDT
// USDT -> ETH,ETC,XMR,ZEC,LTC,XRP -> BTC -> USDT

// USDT -> ETH -> ETC,ZEC -> USDT
// USDT -> ETC,ZEC -> ETH -> USDT

// USDC -> BTC -> USDT,XRP,ETH,LTC,ZEC -> USDC
// USDC -> USDT,XRP,ETH,LTC,ZEC -> BTC -> USDC

class ArbitrageStrategy(implicit as: ActorSystem, mat: ActorMaterializer, ex: ExecutionContext) {
  import ArbitrageStrategy._

  def calc(arbitrage3Step: Arbitrage3Step, returnTicketResponse: Either[PoloniexErrorResponse, ReturnTicketResponse]) =
    Future.successful {
      returnTicketResponse match {
        case Right(response) =>
          calculate(arbitrage3Step, response, fund)
        case Left(response) => None
      }
    }

  val fund = 50

  def infinityLoop(arbitrageSteps: Seq[Arbitrage3Step]): Future[Unit] =
    Source(arbitrageSteps.toList)
      .mapAsyncUnordered(1) { a3s =>
        for {
          ticket <- poloniexPublicApi.returnTicket()
          as     <- calc(a3s, ticket)
          _ <- as match {
            case Some(calculatedArbitrage3Step) if calculatedArbitrage3Step.isPositiveWithFee(riskLevelPrc) =>
              println("Trading", calculatedArbitrage3Step, Instant.now())
              trading(calculatedArbitrage3Step)
            case Some(calculatedArbitrage3Step) =>
              val t = System.nanoTime()
              if (t % 20 == 0) println(Instant.now, calculatedArbitrage3Step, "\n------------------------------------------------")
              Future.successful(Unit)
            case None => Future.successful(Unit)
          }
        } yield ()
      }
      .runWith(Sink.ignore)
      .flatMap { _ =>
        System.gc()
        infinityLoop(arbitrageSteps)
      }

  def runFut(): Unit = {
    val arbitrageSteps = Seq(
      arbitrage3StepUsdtBtcEth,
      arbitrage3Step3
//      arbitrage3Step4
    )

    Await.result(infinityLoop(arbitrageSteps), Duration.Inf)
  }

  def run(): Unit = {
    val arbitrageSteps = Seq(
      arbitrage3StepUsdtBtcEth,
      arbitrage3Step3,
      arbitrage3Step4
    )

    while (true) {

      arbitrageSteps.foreach { a3s =>
        val fut = for {
          ticket <- poloniexPublicApi.returnTicket()
          as     <- calc(a3s, ticket)
          _ <- as match {
            case Some(calculatedArbitrage3Step) if calculatedArbitrage3Step.isPositiveWithFee(riskLevelPrc) =>
              println("Trading", calculatedArbitrage3Step, Instant.now())
              trading(calculatedArbitrage3Step)
            case Some(calculatedArbitrage3Step) =>
              val t = System.nanoTime()
              if (t % 20 == 0) println(Instant.now, calculatedArbitrage3Step, "\n------------------------------------------------")
              Future.successful(Unit)
            case None => Future.successful(Unit)
          }
        } yield ()

        Await.result(fut, Duration.Inf)

        System.gc()
      }
    }

  }

  def tradingNew(step: CalculatedStep): Future[Position] = {
    def loop(orderNumber: Option[OrderNumber], partialPositions: Seq[PartialPosition], retryCounter: Int = 0): Future[Position] =
      orderNumber match {
        case None =>
          poloniexPublicApi.returnTicket().flatMap {
            case Right(returnTicketResponse) =>
              returnTicketResponse.value.get(step.arbitrageStep.poloniexCurrencyPair) match {
                case Some(returnTicket) =>
                  val operationRate   = getPrice(step.arbitrageStep.positionDirection, returnTicket)
                  val recalculateStep = step.calculateForAnotherPrice(operationRate)

                  if (step.arbitrageStep.positionDirection.isLong)
                    poloniexTradingApi.buy(step.arbitrageStep.poloniexCurrencyPair, operationRate, recalculateStep.countWithFee).flatMap {
                      case Right(value: BuySuccessResponse) => loop(Some(value.orderNumber), partialPositions)
                      case Right(value: BuyErrorResponse) => loop(None, partialPositions, retryCounter + 1)
                      case Left(value) => loop(None, partialPositions, retryCounter + 1)
                    }
                  else
                    poloniexTradingApi.buy(step.arbitrageStep.poloniexCurrencyPair, operationRate, recalculateStep.countWithFee).flatMap {
                      case Right(value: SellSuccessResponse) => loop(Some(value.orderNumber), partialPositions)
                      case Right(value: SellErrorResponse) => loop(None, partialPositions, retryCounter + 1)
                      case Left(value) => loop(None, partialPositions, retryCounter + 1)
                    }
                case None => loop(None, partialPositions, retryCounter + 1)
              }
            case Left(value) => loop(None, partialPositions, retryCounter + 1)
          }
        case Some(orderNumber) =>
          poloniexTradingApi.returnOrderTrades(orderNumber).map {
            case Right(value) => ???
            case Left(value) => ???
          }

      }

    ???

  }

  case class PositionImpl(seq: Seq[PartialPosition])

  case class PartialPosition(orderNumber: OrderNumber, resultingTrade: Seq[ReturnOrderTrades])

  def trading(calculatedArbitrage3Step: CalculatedArbitrage3Step): Future[Done] =
//    val CalculatedArbitrage3Step(step1, step2, step3) = calculatedArbitrage3Step
//    val positionsTrading = for {
//      position1 <- trading2(step1)
//      _ = println(" FINISH STEP 1")
//      position2 <- trading2(step2)
//      _ = println(" FINISH STEP 2")
//      position3 <- trading2(step3)
//      _ = println(" FINISH STEP 3")
//    } yield (position1, position2, position3)

//    positionsTrading.map {
//      case (Some(position1), Some(position2), Some(position3)) =>
//        println(s"Successful trading. \n Position1: ${position1}. \n Position2: ${position2}. \n Position3: ${position3}")
//        Done
//      case (position1, position2, position3) =>
//        println(s"Error during trading. \n Position1: ${position1}. \n Position2: ${position2}. \n Position3: ${position3}")
//        Done
//    }
    ???

  def getPrice(positionDirection: PositionDirection, returnTicket: ReturnTicket) =
    positionDirection match {
      case PositionDirection.Buy  => returnTicket.lowestAsk
      case PositionDirection.Sell => returnTicket.highestBid
    }

  def calculate(arbitrage3Step: Arbitrage3Step, returnTicketResponse: ReturnTicketResponse, fund: BigDecimal) = {
    val Arbitrage3Step(firstStep, secondStep, thirdStep) = arbitrage3Step
    val fee                                              = BigDecimal(0.2)
    for {
      first <- returnTicketResponse.value.get(firstStep.poloniexCurrencyPair)
      firstPrice          = getPrice(firstStep.positionDirection, first)
      firstStepCalculated = CalculatedStep.calculate(firstStep, firstPrice, fee, fund)
      second <- returnTicketResponse.value.get(secondStep.poloniexCurrencyPair)
      secondPrice          = getPrice(secondStep.positionDirection, second)
      secondStepCalculated = CalculatedStep.calculate(secondStep, secondPrice, fee, firstStepCalculated.countWithFee)
      third <- returnTicketResponse.value.get(thirdStep.poloniexCurrencyPair)
      thirdPrice          = getPrice(thirdStep.positionDirection, third)
      thirdStepCalculated = CalculatedStep.calculate(thirdStep, thirdPrice, fee, secondStepCalculated.countWithFee)
    } yield CalculatedArbitrage3Step(firstStepCalculated, secondStepCalculated, thirdStepCalculated)
  }

  private val apiKey = PoloniexAPIKey("I00DET8U-GKX74ERJ-OXD3EOQ0-F97EPLCX")
  private val secret = PoloniexSecret(
    "d5ac1f9add12731882bc07e1fd9a7360beb2d7829f6b8dc7ab4079b0e1af991538f91b11dfaea752820a794f502e0a548bdefe49a7a26c8b4fb8909acdc413ac")

  private lazy val poloniexPublicApi  = new PoloniexPublicApi
  private lazy val poloniexTradingApi = new PoloniexTradingApi(apiKey, secret)
  private val riskLevelPrc            = BigDecimal(0.00001)

  private val ROUNDING_MODE = BigDecimal.RoundingMode.HALF_DOWN

}

object ArbitrageStrategy {

  trait Step { self =>
    val poloniexCurrencyPair: PoloniexCurrencyPair
    val positionDirection: PositionDirection
  }

  case class ArbitrageStep(poloniexCurrencyPair: PoloniexCurrencyPair, positionDirection: PositionDirection) extends Step

  case class Arbitrage3Step(first: ArbitrageStep, second: ArbitrageStep, third: ArbitrageStep) {
    require(first.positionDirection != third.positionDirection)
    require {
      first.positionDirection match {
        case PositionDirection.Buy  => first.poloniexCurrencyPair.counter == third.poloniexCurrencyPair.counter
        case PositionDirection.Sell => true
      }
    }
    require(
      (first.positionDirection, second.positionDirection) match {
        case (PositionDirection.Buy, PositionDirection.Buy)  => first.poloniexCurrencyPair.base == second.poloniexCurrencyPair.counter
        case (PositionDirection.Buy, PositionDirection.Sell) => first.poloniexCurrencyPair.base == second.poloniexCurrencyPair.base
        case _                                               => true
      }
    )
    require(
      (second.positionDirection, third.positionDirection) match {
        case (PositionDirection.Buy, PositionDirection.Sell)  => second.poloniexCurrencyPair.base == third.poloniexCurrencyPair.base
        case (PositionDirection.Sell, PositionDirection.Sell) => second.poloniexCurrencyPair.counter == third.poloniexCurrencyPair.base
        case _                                                => true
      }
    )
  }

  case class CalculatedArbitrage3Step(calculatedStepFirst: CalculatedStep, calculatedStepSecond: CalculatedStep, calculatedStepThird: CalculatedStep) {
    require(calculatedStepFirst.arbitrageStep.positionDirection != calculatedStepThird.arbitrageStep.positionDirection)

    def isPositiveWithFee(riskLevelPrc: BigDecimal): Boolean = calculatedStepThird.countWithFee >= calcRiskLevel(calculatedStepFirst.fund, riskLevelPrc)

    override def toString: String = s"Step1: ${calculatedStepFirst}\n Step2: ${calculatedStepSecond}\n Step3: ${calculatedStepThird}"
  }

  /**
   *
   * @param count operation count. Amount
   * @param fee   operation fee.
   * @param price operation price. Rate
   * @param fund  operation cost.
   * @param arbitrageStep
   */
  case class CalculatedStep(count: BigDecimal, fee: BigDecimal, price: BigDecimal, fund: BigDecimal, arbitrageStep: ArbitrageStep) {

    def countWithFee = (count * (1 - (fee / 100))).setScale(8, ROUNDING_MODE)

    def calculateForAnotherPrice(anotherPrice: BigDecimal): CalculatedStep = {
      val newValue = (anotherPrice * count) / price
      CalculatedStep(newValue, fee, anotherPrice, fund, arbitrageStep)
    }
  }

  object CalculatedStep {

    def calculate(arbitrageStep: ArbitrageStep, rate: BigDecimal, fee: BigDecimal, fund: BigDecimal) = {
      val count = arbitrageStep.positionDirection match {
        case PositionDirection.Buy  => fund / rate
        case PositionDirection.Sell => fund * rate
      }

      CalculatedStep(count.setScale(8, ROUNDING_MODE), fee, rate, fund, arbitrageStep)
    }

  }

  def getPrice(positionDirection: PositionDirection, returnTicket: ReturnTicket) =
    positionDirection match {
      case PositionDirection.Buy  => returnTicket.lowestAsk
      case PositionDirection.Sell => returnTicket.highestBid
    }

  case class Position(value: BigDecimal, price: BigDecimal, calculatedStep: CalculatedStep)

  val currencyPairUsdtBtc  = PoloniexCurrencyPair.from("USDT", "BTC")
  val currencyPairBtcEth   = PoloniexCurrencyPair.from("BTC", "ETH")
  val currencyPairBtcEtc   = PoloniexCurrencyPair.from("BTC", "ETC")
  val currencyPairBtcXmr   = PoloniexCurrencyPair.from("BTC", "XMR")
  val currencyPairUsdtEth  = PoloniexCurrencyPair.from("USDT", "ETH")
  val currencyPairUsdtEtc  = PoloniexCurrencyPair.from("USDT", "ETC")
  val currencyPairUsdtXmr  = PoloniexCurrencyPair.from("USDT", "XMR")
  val currencyPairUsdcUsdt = PoloniexCurrencyPair.from("USDC", "USDT")
  val currencyPairUsdcBtc  = PoloniexCurrencyPair.from("USDC", "BTC")

  val usdtToBtc = ArbitrageStep(currencyPairUsdtBtc, PositionDirection.Buy)
  val btcToEth  = ArbitrageStep(currencyPairBtcEth, PositionDirection.Buy)
  val ethToUsdt = ArbitrageStep(currencyPairUsdtEth, PositionDirection.Sell)

  val firstStep1  = ArbitrageStep(currencyPairUsdtEth, PositionDirection.Buy)
  val secondStep1 = ArbitrageStep(currencyPairBtcEth, PositionDirection.Sell)
  val thirdStep1  = ArbitrageStep(currencyPairUsdtBtc, PositionDirection.Sell)

  val firstStep2  = ArbitrageStep(currencyPairUsdtBtc, PositionDirection.Buy)
  val secondStep2 = ArbitrageStep(currencyPairBtcEtc, PositionDirection.Buy)
  val thirdStep2  = ArbitrageStep(currencyPairUsdtEtc, PositionDirection.Sell)

  val firstStep3  = ArbitrageStep(currencyPairUsdtEtc, PositionDirection.Buy)
  val secondStep3 = ArbitrageStep(currencyPairBtcEtc, PositionDirection.Sell)
  val thirdStep3  = ArbitrageStep(currencyPairUsdtBtc, PositionDirection.Sell)

  val firstStep4  = ArbitrageStep(currencyPairUsdtBtc, PositionDirection.Buy)
  val secondStep4 = ArbitrageStep(currencyPairBtcXmr, PositionDirection.Buy)
  val thirdStep4  = ArbitrageStep(currencyPairUsdtXmr, PositionDirection.Sell)

  val firstStep5  = ArbitrageStep(currencyPairUsdtXmr, PositionDirection.Buy)
  val secondStep5 = ArbitrageStep(currencyPairBtcXmr, PositionDirection.Sell)
  val thirdStep5  = ArbitrageStep(currencyPairUsdtBtc, PositionDirection.Sell)

  val firstStep6  = ArbitrageStep(currencyPairUsdcUsdt, PositionDirection.Buy)
  val secondStep6 = ArbitrageStep(currencyPairUsdtBtc, PositionDirection.Buy)
  val thirdStep6  = ArbitrageStep(currencyPairUsdcBtc, PositionDirection.Sell)

  val arbitrage3StepUsdtBtcEth = Arbitrage3Step(usdtToBtc, btcToEth, ethToUsdt)
  val arbitrage3Step3          = Arbitrage3Step(firstStep3, secondStep3, thirdStep3)
  val arbitrage3Step4          = Arbitrage3Step(firstStep4, secondStep4, thirdStep4)

  def calcRiskLevel(found: BigDecimal, riskLevelPrc: BigDecimal) = found * (1 + (riskLevelPrc / BigDecimal(100)))

  private val ROUNDING_MODE = BigDecimal.RoundingMode.HALF_DOWN
}
