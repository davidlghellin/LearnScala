package es.david.cap053

import scalaz.Scalaz._
import scalaz.{Order, _}

object OderSalayApp extends App {

  import Salary._

  println(Salary(1.0f) ?|? Salary(2.0f))
  println(Salary(2.0f) ?|? Salary(2.0f))
  println(Salary(3.0f) ?|? Salary(2.0f))

  //  // Podemos ordernar
  implicit val salaryOrdering = Order[Salary].toScalaOrdering
  println(List(Salary(2.0f), Salary(1.0f), Salary(0.0f)).sorted)

  // y como order extiende de Equal podemos comparar
  println(Salary(0.0F) === Salary(1.0F))
}

case class Salary(amt: Float)

object Salary {
  implicit val a: Order[Salary] = new Order[Salary] {
    override def order(a: Salary, b: Salary): Ordering = {
      a.amt compare b.amt match {
        case -1 => Ordering.LT
        case 0 => Ordering.EQ
        case 1 => Ordering.GT
      }
    }
  }
}
