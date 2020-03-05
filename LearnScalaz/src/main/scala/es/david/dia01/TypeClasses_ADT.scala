package es.david.dia01

import scalaz.Equal
import scalaz._, Scalaz._

object TypeClasses_ADT extends App {

  // semaforo
  sealed trait TrafficLight

  case object Red extends TrafficLight

  case object Yellow extends TrafficLight

  case object Green extends TrafficLight

  // Definiremos la instancia para Equal
  implicit val TrafficLightEqual: Equal[TrafficLight] = Equal.equal(_ == _)

  //  println(Red === Yellow)
  //  error


  //  Probemos a crear una clase
  case class TrafficLightC(name: String)

  // en este caso estoy perdiendo la coincidencia de patrones
  val red = TrafficLightC("red")
  val yellow = TrafficLightC("yellow")
  val green = TrafficLightC("green")


  implicit val TrafficLightCEqual: Equal[TrafficLightC] = Equal.equal(_ == _)
  println(red === red)
  println(red === yellow)
}
