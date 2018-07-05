package pavlomi.scalatrading.eventemitter

import pavlomi.scalatrading.domain.{PositionEvent, SetStopLostEvent}

abstract class SetStopLostHandler extends EventEmitter[PositionEvent, SetStopLostEvent]
