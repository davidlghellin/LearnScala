package es.david.typeclasses

trait Equal[A] {
  def eq(a: A, b: A): Boolean
}

object Equal {
  def compare[A: Equal](a: A, b: A) = implicitly[Equal[A]].eq(a, b)

  def elementOf[A: Equal](a: A, list: List[A]): Boolean = {
    val m = implicitly[Equal[A]]
    list match {
      case Nil => false
      case (h :: t) => m.eq(a, h) || elementOf(a, t)
    }
  }

  implicit def equalPerson = new Equal[Person] {
    override def eq(a: Person, b: Person): Boolean = a.age == b.age && a.name == b.name
  }

  implicit def equalInt = new Equal[Int] {
    override def eq(a: Int, b: Int): Boolean = a == b
  }

  implicit def equalList[A](implicit equalA: Equal[A]) = new Equal[List[A]] {
    override def eq(a: List[A], b: List[A]): Boolean = {
      (a, b) match {
        case (Nil, Nil) => true
        case (x :: xs, Nil) => false
        case (Nil, y :: ys) => false
        case (x :: xs, y :: ys) => equalA.eq(x, y) && eq(xs, ys)
      }
    }
  }
}

case class Person(name: String, age: Int)

object MainEq extends App {

  val david = Person("David", 34)
  val david2 = Person("David2", 34)
  val listPerson = List(david, david)

  println(Equal.compare(david, david2))
  println(Equal.elementOf(Person("", 4), listPerson))

  val listInt = List(1, 2, 3, 4, 5, 9)
  println(Equal.elementOf(9, listInt))

  println(Equal.compare(listInt, listInt))
  println(Equal.compare(listInt, listInt ::: List(1)))
}

