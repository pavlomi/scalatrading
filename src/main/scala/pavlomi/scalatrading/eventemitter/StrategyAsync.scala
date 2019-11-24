package pavlomi.scalatrading.eventemitter
import pavlomi.scalatrading.domain.Event

abstract class StrategyAsync[IN <: Event, OUT <: Event] extends EventEmitterAsync[IN, OUT]
