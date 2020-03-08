package es.david.typeclasses

sealed trait Ordering2

case object LT extends Ordering2

case object EQ extends Ordering2

case object GT extends Ordering2

trait Order[A] {
  def compare(a: A, b: A): Ordering2
}

object Order {
  def compare[A: Order](a: A, b: A) = implicitly[Order[A]].compare(a, b)

  implicit def orderInt = new Order[Int] {
    override def compare(a: Int, b: Int): Ordering2 =
      if (a < b) LT
      else if (a == b) EQ
      else GT

  }


  implicit def orderString = new Order[String] {
    override def compare(a: String, b: String): Ordering2 =
      if (a < b) LT
      else if (a == b) EQ
      else GT
  }


  implicit def orderPersonByAge = new Order[Person] {
    override def compare(a: Person, b: Person): Ordering2 = {
      implicitly[Order[Int]].compare(a.age, b.age) match {
        case LT => LT
        case GT => GT
        case EQ => implicitly[Order[String]].compare(a.name, b.name)
      }
    }
  }

  object a {
    // nos dicen que ordernos por el nombre de la persona
    implicit def orderPersonByName = new Order[Person] {
      override def compare(a: Person, b: Person): Ordering2 = {
        implicitly[Order[String]].compare(a.name, b.name) match {
          case LT => LT
          case GT => GT
          case EQ => implicitly[Order[Int]].compare(a.age, b.age)
        }
      }
    }
  }


}

object MainOrdering extends App {
  val david = Person("David66", 31)
  val david2 = Person("David2", 34)
  val listPerson = List(david, david)
  println(Order.compare(david, david2))

  // al añadir el order por nombre PETA
  // por lo que hemos creado un object para solucionarlo (puede que no sea lo correcto)

  import es.david.typeclasses.Order.a._

  println(Order.compare(david, david2))
}