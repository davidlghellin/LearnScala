package es.david.intro

import shapeless._

object EjemploIntroShapless extends App {

  // trabajando con listas y tuplas VS listas heterogeneas
  val l = 10 :: "string" :: 1.0 :: Nil
  val hl = 10 :: "string" :: 1.0 :: HNil

  val t = (1, "String", 2.0)
  val hlt = 1 :: "String" :: 2.0 :: HNil

  val (a, b, c) = t
  val x :: y :: z :: HNil = hl

  t match {
    case (1, s, _) => println(s)
    case _=>
  }

  hl match {
    case 1 :: s :: _ :: HNil => println(s)
    case _=>
  }
}
