package pavlomi.scalatrading.eventemitter
import pavlomi.scalatrading.domain.Event

import scala.concurrent.Future

trait EventEmitterAsync[IN <: Event, OUT <: Event] {
  def execute(event: IN): Future[OUT]
}
