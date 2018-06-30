package pavlomi.scalatrading.eventemitter

import pavlomi.scalatrading.domain.{MarketEvent, SignalEvent}

abstract class RiskManager extends EventEmitter[SignalEvent, MarketEvent]
