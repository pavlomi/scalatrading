package pavlomi.scalatrading.eventemitter
import pavlomi.scalatrading.domain.Event

trait PortfolioHandlerAsync[IN <: Event, OUT <: Event] extends EventEmitterAsync[IN, OUT]
