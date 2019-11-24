package pavlomi.scalatrading.eventemitter
import pavlomi.scalatrading.domain.Event

trait OrderHandlerAsync [IN <: Event, OUT <: Event] extends EventEmitterAsync[IN, OUT]
