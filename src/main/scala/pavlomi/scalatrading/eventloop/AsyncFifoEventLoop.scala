package pavlomi.scalatrading.eventloop

import java.util.concurrent.atomic.AtomicReference

import pavlomi.scalatrading.domain.{CombinedEvent, EmptyEvent, Event}

import scala.collection.immutable.Queue

trait AsyncFifoEventLoop { self: AsyncEventLoop =>

  private val ar = new AtomicReference(Queue.empty[Event])

  override def getNextEvent: Event = {
    val (event, queue) = ar.get.dequeue
    ar.set(queue)
    event
  }
  override def isEmpty: Boolean = ar.get.isEmpty

  override def addEvent(event: Event): Unit = event match {
    case EmptyEvent => Unit
    case CombinedEvent(first, second) =>
      addEvent(first)
      addEvent(second)
    case e =>
      val queue = ar.get
      ar.set(queue.enqueue(e))
  }
}
