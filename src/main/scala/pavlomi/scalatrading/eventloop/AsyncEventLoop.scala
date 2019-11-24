package pavlomi.scalatrading.eventloop
import pavlomi.scalatrading.domain.Event

import scala.concurrent.{ExecutionContext, Future}

trait AsyncEventLoop {

  def execute(dataEvent: Event): Unit

  protected def addEvent(event: Event): Unit

  protected def getNextEvent: Event

  protected def isEmpty: Boolean

  protected def isNotEmpty: Boolean = !isEmpty

  protected def run(): Future[Unit] = {
    def loop(acc: Seq[Event]): Seq[Event] = if (isEmpty) acc else loop(acc :+ getNextEvent)

    val events = loop(Seq.empty[Event])

    Future.traverse(events)(eventProcessing).map { events =>
      events.map(addEvent)
      Unit
    }

  }

  protected def eventProcessing(event: Event): Future[Event]

  implicit val ec: ExecutionContext
}
