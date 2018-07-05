package pavlomi.scalatrading.eventemitter

import pavlomi.scalatrading.domain.Event

trait EventEmitter[E <: Event, T <: Event] {
  def execute(event: E): Seq[T]
}
