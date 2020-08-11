package es.david.cap054

import scalaz.Scalaz._
import scalaz.{EphemeralStream, IList, Monoid}

object FoldableApp extends App {

  val l: IList[Int] = IList(1, 2, 3)
  // Reducimos
  println(l.foldMap(identity)(Monoid[Int]))
  println(l.foldMap(_ + 1)(Monoid[Int]))

  println(l.foldLeft(0L)((a, b) => a + b))

  def isSmall(i: Int): Boolean = i < 20

  // problema de stackoverflow
  //  val resultStackOverflow: Boolean = (1 until 100000).toStream.foldRight(false) {
  //    (el, acc) => isSmall(el) || acc
  //  }

  // solucionado usando byname , que solamente calcula acc cuando es necesario
  val unoToMillones: EphemeralStream[Int] = 1 |=> 10000 // stream infitino Scalaz
  val resultIsSmall = (1 |=> 10000).foldRight(false) {
    el => acc => isSmall(el) || acc
  }
  println(resultIsSmall)

  // def intercalate[A: Monoid](fa: F[A], a: A): A = ...
  val intercale = List("foo", "bar").intercalate(",")
  println(intercale === "foo,bar")

  // def index(n: Int): Option[A] = F.index(self, n)
  println(l.index(23))
  println(l.indexOr(default = -99, 23))

  println(l.length)
  println(l.count(_ > 0))
  println(l.empty)
  println(l.element(2))
  println(l.filterLength(_ > 1)) // cuenta
  println(l.all(_ > 1))          // todos cumplen la condicion
  println(l.any(_ > 1))          // alguno cumplen la condicion
}
