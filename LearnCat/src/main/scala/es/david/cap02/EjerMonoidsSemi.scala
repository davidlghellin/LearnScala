package es.david.cap02

import cats.Monoid
import cats.instances.int._
import cats.instances.string._

import scala.annotation.tailrec // for Monoid
object EjerMonoidsSemi extends App {

  def add(items: List[Int]): Int =
    items.foldLeft(Monoid[Int].empty)(Monoid[Int].combine(_, _))

  println(add(List(1, 2, 3, 4, 5)))

  def add(items: List[String]): String =
    items.foldLeft(Monoid[String].empty)(Monoid[String].combine(_, _))


  import cats.instances.option._
  import cats.syntax.semigroup._ // for |+|
  def add(items: List[Option[Int]]): Option[Int] =
    items.foldLeft(Monoid[Option[Int]].empty)(_ |+| _)

  case class Order(totalCost: Double, quantity: Double)

  implicit val monoidOrder: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0, 0)

    override def combine(x: Order, y: Order): Order =
      Order(x.totalCost + y.totalCost
        , x.quantity + y.quantity
      )
  }

  val mult3: List[String] = List.fill(5)(List("", "", "Fizz")).flatten
  val mult5: List[String] = List.fill(3)(List("", "", "", "", "Buzz")).flatten
  val zipp: List[(String, String)] = mult3.zip(mult5)

  Monoid[String].combine("Hi ", "there")

  println(Monoid[String].combine("Hi ", "there"))


  println("Hi" |+| "there" |+| Monoid[String].empty)
  println("Hi" |+| "there")

  def sumTupla(items: List[(String, String)]): List[String] = {
    items.map(x => x._1 |+| x._2)
    //items.map(x=> Monoid[String].combine(x._1,x._2))
  }

  println(sumTupla(zipp))

  def zipWith[A, B, C](xs: List[A], ys: List[B], f: (A, B) => C): List[C] = {
    @tailrec
    def zipAccumulatingResult(xs: List[A], ys: List[B], f: (A, B) => C, acc: List[C]): List[C] = {
      (xs, ys) match {
        case (Nil, _) => acc
        case (_, Nil) => acc
        case (x :: xs, y :: ys) => zipAccumulatingResult(xs, ys, f, acc :+ f(x, y))
      }
    }

    zipAccumulatingResult(xs, ys, f, Nil)
  }

  val combiFun: (String, String) => Option[String] = (a, b) => {
    (a, b) match {
      case (a1, b1) if a1 != "" && b1 != "" => Some(a1.concat(b1))
      case (a, _) if a != "" => Some(a)
      case (_, a) if a != "" => Some(a)
      case (_, _) => None
    }
  }

  println(zipWith[String, String, Option[String]](mult3, mult5, combiFun))

}
