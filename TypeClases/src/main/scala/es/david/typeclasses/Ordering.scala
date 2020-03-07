package es.david.typeclasses

import scala.collection.mutable

@scala.annotation.implicitNotFound(
  "Necesitamos un implicito Ordering[${T}] en el contexto"
)
trait Ordering[T] {
  def isLessThan(left: T, right: T): Boolean
}

object Main extends App {

  def sort[T](items: Seq[T])(implicit ord: Ordering[T]): Seq[T] = {
    val buffer = mutable.ArrayBuffer(items: _*)
    for (i <- 0 until items.size;
         j <- (i + 1) until items.size)
      if (ord.isLessThan(buffer(j), buffer(i))) {
        val temp = buffer(i)
        buffer(i) = buffer(j)
        buffer(j) = temp
      }
    buffer
  }

  implicit object IntOrdering extends Ordering[Int] {
    override def isLessThan(left: Int, right: Int): Boolean = left < right
  }

  val numbers = Seq(4, 1, 10, 8, 14, 2)
  println(sort(numbers))
  assert(sort(numbers) == Seq(1, 2, 4, 8, 10, 14))

  // Creamos Persona y qyeremos ordernar por edad
  case class Person(name: String, age: Int)

  implicit object PersonOrdering extends Ordering[Person] {
    override def isLessThan(left: Person, right: Person): Boolean = left.age < right.age
  }

  val agus = Person("Agustin", 44)
  val dav = Person("David", 1)
  val ces = Person("Cesar", 12)
  assert(sort(Seq(agus, dav, ces)) == Seq(dav, ces, agus))
}
