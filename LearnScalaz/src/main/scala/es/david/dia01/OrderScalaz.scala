package es.david.dia01

// http://fasihkhatib.com/2017/06/29/scalaz-order/
import scalaz.Scalaz._
import scalaz._

object OrderScalaz extends App {

  case class Salary(amt: Float)

  implicit object SalaryOrder extends Order[Salary] {
    override def order(a: Salary, b: Salary): Ordering = {
      a.amt compare b.amt match {
        case -1 => Ordering.LT
        case 0 => Ordering.EQ
        case 1 => Ordering.GT
      }
    }
  }

  println(Salary(1.0f) ?|? Salary(2.0f))
}
