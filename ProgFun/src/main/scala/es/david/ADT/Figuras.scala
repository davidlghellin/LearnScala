package es.david.ADT

sealed trait Figuras

final case class Cuadrado(lado: Int) extends Figuras
final case class Rectangulo(alto: Int, ancho: Int) extends Figuras
final case class Circulo(radio: Int) extends Figuras

object Figuras {
  def area(f: Figuras): Double = {
    f match {
      case Cuadrado(a) => a * a
      case Rectangulo(a, b) => a * b
      case Circulo(r) => 2 * Math.PI * r
    }
  }
}

object FigurasMain extends App {
  val c1: Cuadrado = Cuadrado(2)
  val r1: Rectangulo = Rectangulo(2, 3)

  println(Figuras.area(c1))
  println(Figuras.area(r1))
}