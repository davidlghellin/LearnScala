package es.david.cap053

import scalaz.Order
import scalaz.Scalaz._

object OrderPor extends App {

  case class XXX(str: String, f: Float, n: Int)

  import scalaz.Order.orderBy

  val orderByStr: Order[XXX] = orderBy { c: XXX => c.str }
  val orderByF: Order[XXX] = orderBy { c: XXX => c.f }
  val orderByN: Order[XXX] = orderBy(_.n)

  val combinedOrder: Order[XXX] = orderByStr |+| orderByF |+| orderByN

  println(combinedOrder(XXX("a", 1.0f, 2), XXX("a", 1.0f, 2))) // EQ
  println(combinedOrder(XXX("a", 1.0f, 3), XXX("a", 1.0f, 2))) // GT
  println(combinedOrder(XXX("a", 1.0f, 2), XXX("a", 1.0f, 1))) // GT
  println(combinedOrder(XXX("a", 1.0f, 1), XXX("a", 1.0f, 2))) // LT
  println(combinedOrder(XXX("ab", 0.0f, 1), XXX("abc", 0.0f, 1))) // LT

  implicit val XXXOrdering = combinedOrder.toScalaOrdering
  val l = List(XXX("ab", 0.0f, 1), XXX("abc", 0.0f, 1), XXX("defg", 1.0f, 2), XXX("ab", 1.0f, 1), XXX("abc", 1.0f, 2), XXX("defg", 1.0f, 1))
  println(l.sorted)
}
