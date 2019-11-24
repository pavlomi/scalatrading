package pavlomi.scalatrading.domain

trait ExchangeRequest {
  val orderType: String
  val stockSymbol: StockSymbol
  val value: Value
  val price: Price
  val positionDirection: PositionDirection
}

case class MarketRequest(
  stockSymbol: StockSymbol,
  price: Price,
  value: Value,
  positionDirection: PositionDirection
) extends ExchangeRequest {
  val orderType: String = "Market"
}

case class LimitRequest(
  stockSymbol: StockSymbol,
  price: Price,
  value: Value,
  positionDirection: PositionDirection
) extends ExchangeRequest {
  val orderType: String = "Limit"
}
