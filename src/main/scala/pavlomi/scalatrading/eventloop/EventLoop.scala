package pavlomi.scalatrading.eventemitter

import akka.Done
import pavlomi.scalatrading.domain.{DataEvent, Event}

import scala.concurrent.{ExecutionContext, Future}

abstract class EventLoop(implicit ec: ExecutionContext) {
  def execute(dataEvent: DataEvent): Future[Done]

  protected def addEvent(event: Event): Unit

  protected def getNextEvent(): Event

  protected def isEmpty: Boolean

  protected def isNotEmpty: Boolean = !isEmpty

  protected def run(): Unit =
    while (isNotEmpty) {
      val event    = getNextEvent()
      val newEvent = eventProcessing(event)
      addEvent(newEvent)
    }

  protected def eventProcessing(event: Event): Event
}
