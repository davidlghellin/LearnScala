package es.david.typeclasses

object PrintableEjemplo2 extends App {

  case class Cat(nombre: String, age: Int, color: String)

  trait Printable[T] {
    def format(value: T): String
  }

  object PrintableInstances {
    implicit val catInstance = new Printable[Cat] {
      override def format(value: Cat): String = s"${value.nombre} tiene ${value.age} años y es de color ${value.color} "
    }
  }
  implicit class PrintableSyntax[T](value: T) {
    def print(implicit printable: Printable[T]) = println(printable.format(value))
  }

  import PrintableInstances._
  Cat("kityy",3,"negro").print
}
