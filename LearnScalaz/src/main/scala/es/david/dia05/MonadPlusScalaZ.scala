package es.david.dia05


import scalaz.Scalaz._

object MonadPlusScalaZ extends App {
  val forFiltro = for {
    x <- 1 |-> 50 if x.shows contains '7'
  } yield x
  println(forFiltro)
  /*
  La MonadPlusclase de tipo es para mónadas que también pueden actuar como monoides.
   */
  val forFiltroPlus = for {
    x <- 1 |-> 50 filter{ x=> x.shows contains '7'}
  } yield x
  println(forFiltroPlus)
  println()
  println()
}
