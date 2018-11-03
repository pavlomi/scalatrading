package pavlomi.scalatrading.eventemitter

import pavlomi.scalatrading.domain.Event

trait EventEmitter[IN <: Event, OUT <: Event] {
  def execute(event: IN): OUT
}
