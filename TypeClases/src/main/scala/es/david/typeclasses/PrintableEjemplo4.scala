package es.david.typeclasses

import es.david.typeclasses.PrintableEjemplo2.Cat

object PrintableEjemplo4 extends App {

  case class Cat(nombre: String, age: Int, color: String)

  object ShowInstances {
    implicit val catInstance = new cats.Show[Cat] {
      override def show(value: Cat): String = s"${value.nombre} tiene ${value.age} años y es de color ${value.color} "
    }
  }

  implicit class ShowSintax[T](value: T) {
    def show(implicit s: cats.Show[T]): String = s.show(value)
  }

  import ShowInstances._

  println(Cat("kityy", 3, "negro").show)

}
