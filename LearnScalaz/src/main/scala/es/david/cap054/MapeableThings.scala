package es.david.cap054

import scalaz.std.list._
import scalaz.syntax.functor._
import scalaz._

object MapeableThings extends App {
  import Scalaz._
  val l = List(1, 2, 3)
  println(s"l.void:            ${l.void}")
  println(s"l.fproduct(_ + 1): ${l.fproduct(_ + 1)}")
  println(s"l ∘ (_ + 1)        ${l ∘ (_ + 1)}")
  println(s"l.fpair            ${l.fpair}")
//  println(s"l.strengthL("a")   ${l.strengthL("a")}")
//  println(s"l.strengthR("a")   ${l.strengthR("a")}")
  println(s"l.lift             ${l.lift}")
}
