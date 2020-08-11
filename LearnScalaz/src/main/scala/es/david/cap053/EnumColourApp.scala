package es.david.cap053

import scalaz.Ordering._
import scalaz._

object EnumColourApp extends App {

  import Coloring._

  val rojo = RED
  println(rojo)

  implicit val sizeOrdering = Order[Coloring].toScalaOrdering
  println(List(BLUE, rojo, rojo).sorted)

  println(Enum[Coloring].succ(BLUE))
  println(Enum[Coloring].succn(2, BLUE))
  println(Enum[Coloring].fromToL(BLUE, RED))
}

case class Coloring(val toInt: Int, val name: String)

object Coloring extends ColoringInstances {

  val RED = Coloring(1, "RED")
  val BLUE = Coloring(1, "BLUE")
  val GREEN = Coloring(1, "GREEN")
}

// Otra forma de crear la TypeClass
sealed abstract class ColoringInstances {

  import Coloring._

  implicit val coloringInstance: Enum[Coloring] with Show[Coloring] = new Enum[Coloring] with Show[Coloring] {

    def order(a1: Coloring, a2: Coloring): Ordering = (a1, a2) match {
      case (RED, RED) => EQ
      case (RED, BLUE | GREEN) => LT
      case (BLUE, BLUE) => EQ
      case (BLUE, GREEN) => LT
      case (BLUE, RED) => GT
      case (GREEN, RED) => GT
      case (GREEN, BLUE) => GT
      case (GREEN, GREEN) => EQ
      case (_, _) => EQ
    }

    def append(c1: Coloring, c2: => Coloring): Coloring = c1 match {
      case Coloring.RED => c2
      case o => o
    }

    override def shows(c: Coloring) = c.name

    def zero: Coloring = Coloring.RED

    def succ(c: Coloring) = c match {
      case Coloring.RED => Coloring.BLUE
      case Coloring.BLUE => Coloring.GREEN
      case Coloring.GREEN => Coloring.RED
      case _ => zero
    }

    def pred(c: Coloring) = c match {
      case Coloring.GREEN => Coloring.BLUE
      case Coloring.BLUE => Coloring.RED
      case Coloring.RED => Coloring.GREEN
      case _ => zero
    }

    override def max = Some(GREEN)

    override def min = Some(RED)

  }
}