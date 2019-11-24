package pavlomi.scalatrading.eventemitter
import pavlomi.scalatrading.domain.Event

trait RiskManagerHandlerAsync[IN <: Event, OUT <: Event] extends EventEmitterAsync[IN, OUT]
