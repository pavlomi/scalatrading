package pavlomi.scalatrading.eventemitter

import pavlomi.scalatrading.domain.{PositionEvent, SetTakeProfitEvent}

abstract class SetTakeProfitHandler extends EventEmitter[PositionEvent, SetTakeProfitEvent]
