package pavlomi.scalatrading.eventemitter
import pavlomi.scalatrading.domain.Event

trait OrderHandler[IN <: Event, OUT <: Event] extends EventEmitter[IN, OUT] {
  def orderHttpProvider: OrderHttpProvider
}
