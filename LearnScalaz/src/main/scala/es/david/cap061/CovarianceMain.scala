package es.david.cap061

import scalaz.IList

import scala.collection.immutable

object CovarianceMain extends App {

  // https://docs.scala-lang.org/tour/variances.html

  class Foo[+A] // A covariant class     // supertipo
  class Bar[-A] // A contravariant class // subtipo
  class Baz[A]  // An invariant class

  abstract class Animal {
    def name: String
  }

  case class Cat(name: String) extends Animal

  case class Dog(name: String) extends Animal
  def printAnimalNames(animals: List[Animal]): Unit = {
    animals.foreach { animal =>
      println(animal.name)
    }
  }

  val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
  val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))

  printAnimalNames(cats)
  // Whiskers
  // Tom

  printAnimalNames(dogs)
  // Fido
  // Rex

  val animales: List[Animal] = cats ::: dogs
}

object CovariancMain2 extends App {

  case class ListSupe[+A](list: List[A])

  val listaSupe: ListSupe[Any] =
    ListSupe(List("hola", ' ', "que", ' ', "tal"))


  val listaI: IList[Any] =
    IList("hola").widen[Any] ++
      IList(' ').widen[Any]
}