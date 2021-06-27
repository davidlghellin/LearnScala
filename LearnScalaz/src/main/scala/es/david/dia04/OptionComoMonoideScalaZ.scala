package es.david.dia04

import scalaz.Scalaz._
import scalaz._

object OptionComoMonoideScalaZ extends App {
  println((none: Option[String]) |+| "andy".some)
  println(List(1, 2, 3) foldMap {identity})


  //foldable
  val lista: List[Option[String]] = List("hola".some, none,"andy".some, none)
  println(lista foldMap {identity})

  println(List(true, false, true, true) foldMap {Tags.Disjunction.apply})
}
