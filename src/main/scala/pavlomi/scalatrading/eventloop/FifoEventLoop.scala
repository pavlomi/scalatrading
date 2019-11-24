package pavlomi.scalatrading.eventloop

import pavlomi.scalatrading.domain.{CombinedEvent, EmptyEvent, Event}
import pavlomi.scalatrading.eventemitter.EventLoop

import scala.collection.mutable.Queue

trait FifoEventLoop { self: EventLoop =>

  private val fifoQueue: Queue[Event] = Queue.empty[Event]

  override def getNextEvent: Event = fifoQueue.dequeue()
  override def isEmpty: Boolean      = fifoQueue.isEmpty
  override def addEvent(event: Event): Unit = event match {
    case EmptyEvent => Unit
    case CombinedEvent(first, second) =>
      addEvent(first)
      addEvent(second)
    case e          => fifoQueue.enqueue(e)
  }
}
