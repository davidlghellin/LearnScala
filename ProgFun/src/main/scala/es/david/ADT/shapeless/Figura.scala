package es.david.ADT.shapeless

sealed trait Figura

final case class Rectangulo(ancho: Double, alto: Double) extends Figura

final case class Circulo(radio: Double) extends Figura

object Figura {
  def area(figura: Figura): Double = figura match {
    case Circulo(r) => math.Pi * r * r
    case Rectangulo(ancho, alto) => ancho * alto
  }
}

object FiguraAlternativa {
  type Rectangle2 = (Double, Double)
  type Circle2 = Double
  type Shape2 = Either[Rectangle2, Circle2]

  val rect2: Shape2 = Left((3.0, 4.0))
  val circ2: Shape2 = Right(1.0)

  // esta version es mas generica ya que cualquier tupla de dobles es un rectangulo
  // para serializar por ejemplo
  def area2(shape: Shape2): Double =
    shape match {
      case Left((w, h)) => w * h
      case Right(r) => math.Pi * r * r
    }
}