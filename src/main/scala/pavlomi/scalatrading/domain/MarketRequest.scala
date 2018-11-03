package pavlomi.scalatrading.domain

case class MarketRequest(tpe: String, stockSymbol: StockSymbol, price: Price, positionDirection: PositionDirection)
