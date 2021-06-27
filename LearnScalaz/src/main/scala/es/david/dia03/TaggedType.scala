package es.david.dia03

import scalaz.{@@, Tag}

object TaggedType extends App {


  sealed trait KiloGram

  def KiloGram[A](a: A): A @@ KiloGram = Tag[A, KiloGram](a)

  val mass = KiloGram(20.0)
  val massI = KiloGram(20)
  println(mass)
  println(massI)
  println(2 * Tag.unwrap(mass))
}
