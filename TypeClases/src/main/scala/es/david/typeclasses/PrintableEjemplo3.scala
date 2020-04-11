package es.david.typeclasses


import cats.instances.int._
import cats.instances.string._

object PrintableEjemplo3 extends App {

  val showInt: cats.Show[Int] = cats.Show[Int]
  val showString: cats.Show[String] = cats.Show[String]


  println(showInt.show(2))
  println(showString.show("sss"))

  import cats.syntax.all._

  println("hola desde aqui".show)
}
