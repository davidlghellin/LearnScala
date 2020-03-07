package es.david.typeclasses

object Semaforo extends App {

  sealed abstract class TrafficLight

  object TrafficLight {
    case object Red extends TrafficLight
    case object Yellow extends TrafficLight
    case object Green extends TrafficLight
  }

  trait TypeClass[T] {
    def asString(t: T): String
  }


  def algorithm[T](t: T)(implicit e: TypeClass[T]): String = e.asString(t)
  def algorithm2[T: TypeClass](t: T): String = implicitly[TypeClass[T]] asString (t)


  implicit val instance: TypeClass[TrafficLight] = {
    case TrafficLight.Red    => Console.RED    + "Red"   + Console.RESET
    case TrafficLight.Green  => Console.GREEN  + "Green" + Console.RESET
    case TrafficLight.Yellow => Console.YELLOW + "Yellow"+ Console.RESET
  }

  val red: TrafficLight = TrafficLight.Red
  val green: TrafficLight = TrafficLight.Green
  val yellow: TrafficLight = TrafficLight.Yellow

  println(red)
  println(green)
  println(yellow)

  val moreRed: TrafficLight.Red.type = TrafficLight.Red
  implicit val instanceForRed:TypeClass[TrafficLight.Red.type]=
    _=> Console.RED    + "Red"   + Console.RESET
  println(moreRed)
}
