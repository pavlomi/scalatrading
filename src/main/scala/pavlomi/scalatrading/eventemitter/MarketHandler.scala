package pavlomi.scalatrading.eventemitter

import pavlomi.scalatrading.domain.{MarketEvent, PositionEvent}

abstract class MarketHandler extends EventEmitter[MarketEvent, PositionEvent]
