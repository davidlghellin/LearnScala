package es.david.dia05

import scalaz.Scalaz._
import scalaz._

object MonadScalaZ extends App {
  // Monad extiende de Bind y aplicativo
  // Veamos Bind Unir
  println(3.some flatMap { x => (x + 1).some })
  println((none: Option[Int]) flatMap { x => (x + 1).some })

  //Monada
  println(Monad[Option].point("WHAT"))
  println(9.some flatMap { x => Monad[Option].point(x * 10) })
  println((none: Option[Int]) flatMap { x => Monad[Option].point(x * 10) })
  println(Monad[List].point((1.some, none,2.some, none)))
}
