package es.david.typeclasses

@scala.annotation.implicitNotFound(
  "No tenemos forma de comparar ${L} con ${R}." +
    "Necesitamos un implicito TypleClassEquality[${L}, ${R}] en el contexto"
)
trait TypleClassEquality[L, R] {
  def equals(left: L, right: R): Boolean
}

object TypleClassEquality {
  def isEqual[L, R](left: L, right: R)
                   (implicit ev: TypleClassEquality[L, R]): Boolean = {
    ev.equals(left, right)
  }


  implicit def sameTypeEq[T] = new TypleClassEquality[T, T] {
    override def equals(left: T, right: T): Boolean = left.equals(right)
  }

  implicit def intEqualsString = new TypleClassEquality[Int, String] {
    override def equals(left: Int, right: String): Boolean = left.toString == right
  }
}

object Main extends App {

  println(TypleClassEquality.isEqual(3, 3))

  // Hemos agregado la anotacion para que se vea mejor el error que lanzaria
  // println(TypleClassEquality.isEqual(3.1415936,"Pi"))


  implicit object doubleEqualsString extends TypleClassEquality[Double, String] {
    override def equals(left: Double, right: String): Boolean = left.toString == right
  }

  println(TypleClassEquality.isEqual(3D, "3")) // false
  println(TypleClassEquality.isEqual(3D, "3.0")) // true
  println(TypleClassEquality.isEqual(3.1415936, "Pi"))
}