package es.david.ADT.essentialScalaBook.ejercicios

trait TrafficLightPolimorfismo {
  def next: TrafficLightPolimorfismo
}


case object RedPoli extends TrafficLightPolimorfismo {
  override def next: TrafficLightPolimorfismo = GreenPoli
}

case object YellowPoli extends TrafficLightPolimorfismo {
  override def next: TrafficLightPolimorfismo = RedPoli
}

case object GreenPoli extends TrafficLightPolimorfismo {
  override def next: TrafficLightPolimorfismo = YellowPoli
}


sealed trait TrafficLight {
  def next: TrafficLight =
    this match {
      case Red => Yellow
      case Yellow => Green
      case Green => Red
    }

}

case object Red extends TrafficLight
case object Yellow extends TrafficLight
case object Green extends TrafficLight

