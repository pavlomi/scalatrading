package pavlomi.scalatrading.eventemitter

import pavlomi.scalatrading.domain.Event

abstract class SetStopLostHandler[IN <: Event, OUT <: Event] extends EventEmitter[IN, OUT]
