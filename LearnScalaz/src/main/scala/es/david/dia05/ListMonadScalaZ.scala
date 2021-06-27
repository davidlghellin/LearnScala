package es.david.dia05

import scalaz.Scalaz._

object ListMonadScalaZ extends App {
  val lista = List(1, 2, 3)
  println(^(lista, List(10, 100, 1000))(_ * _))
  println()
  println(lista >>= { x => List(x, -x) })
  println()

  val tuplaVisionMonadica = for {
    n <- List(1, 2)
    ch <- List('a', 'b')
  } yield (n, ch)
  println(tuplaVisionMonadica)
}
