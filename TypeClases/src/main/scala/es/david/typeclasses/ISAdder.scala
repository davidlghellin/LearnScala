package es.david.typeclasses

// https://typelevel.org/blog/2017/12/20/who-implements-typeclass.html

trait ISAdder[A] {

}

object ISAdder {

  implicit object AddInts extends ISAdder[Int]

  implicit object AddStrs extends ISAdder[String]

  final case class AddVects[E]() extends ISAdder[Vector[E]]

  implicit def addVects[E]: ISAdder[Vector[E]] = AddVects()


  def isadd[A: ISAdder](x: A, y: A): A =
    implicitly[ISAdder[A]] match {
      case ISAdder.AddInts => x + y
      case ISAdder.AddStrs => s"$x$y"
      // case ISAdder.AddVects() => x ++ y
      // Patron tipo variable
      case _: ISAdder.AddVects[e] => (x: Vector[e]) ++ y
    }
}

object MainIsAdder extends App {
  println(ISAdder.isadd(2, 2))
  println(ISAdder.isadd("3", "4"))
  println(ISAdder.isadd(Vector(1, 1), Vector(0, 0)))
}

