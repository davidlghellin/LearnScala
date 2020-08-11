package es.david.cap053

import scalaz.Ordering._
import scalaz.Scalaz._
import scalaz._

object OrderFooApp extends App {

  import Foo._

  (1 < 2).println
  ("a" < "b").println

  println(Foo("a") < Foo("b"))

  println(Foo("a") max Foo("b"))

  println(Foo("a") ?|? Foo("b"))
  // LT

  Foo("a") ?|? Foo("b") match {
    case LT | EQ => println("see?") //
    case GT => "what?  How did it get here?!?!?!".println
  }
}

case class Foo(name: String)

object Foo {

  implicit val fooOrder: Order[Foo] = Order.orderBy(_.name)
  // veamos la alternativa especificando nosotros
  val fooOrder2: Order[Foo] = new Order[Foo] {
    override def order(x: Foo, y: Foo): Ordering = {
      x.name compare y.name match {
        case -1 => Ordering.LT
        case 0 => Ordering.EQ
        case 1 => Ordering.GT
      }
    }
  }
}
