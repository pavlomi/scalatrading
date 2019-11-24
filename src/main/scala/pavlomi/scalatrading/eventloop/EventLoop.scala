package pavlomi.scalatrading.eventemitter

import pavlomi.scalatrading.domain.Event

abstract class EventLoop {
  def execute(dataEvent: Event): Unit

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
