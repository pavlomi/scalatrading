package pavlomi.scalatrading.domain

case class MarketRequest(
  orderType: String,
  stockSymbol: StockSymbol,
  price: Price,
  positionDirection: PositionDirection
)
