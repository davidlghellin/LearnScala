package es.david.cap054

import scalaz.Scalaz._
import scalaz._

object MapeableThings extends App {

  val l = List(1, 2, 3)
  val listVoid: List[Unit] = l.void
  println(s"l.void:            ${listVoid}")
  println(listVoid === List((), (), ()))
  println()

  val listProduct: List[(Int, Int)] = l.fproduct(_ + 1)
  println(s"l.fproduct(_ + 1): ${listProduct}")
  println(listProduct === List((1, 2), (2, 3), (3, 4)))
  println()

  val listMap: List[Int] = l ∘ (_ + 1)
  println(s"l ∘ (_ + 1)        ${listMap}")
  println(listMap === List(2, 3, 4))
  println()

  val listPair: List[(Int, Int)] = l.fpair
  println(s"l.fpair            ${listPair}")
  println(listPair === List((1, 1), (2, 2), (3, 3)))
  println()
  //  println(s"l.strengthL("a")   ${l.strengthL("a")}")
  //  println(s"l.strengthR("a")   ${l.strengthR("a")}")
  println(s"l.lift             ${l.lift}")
}
