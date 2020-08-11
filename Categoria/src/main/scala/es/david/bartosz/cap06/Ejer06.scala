package es.david.bartosz.cap06

object Ejer06 extends App {

  sealed trait Figura

  case class Circulo(radio: Float) extends Figura

  case class Rectangulo(lado1: Float, lado2: Float) extends Figura

  def calculaArea(figura: Figura): Double = figura match {
    case c: Circulo => Math.PI * c.radio * c.radio
    case r: Rectangulo => r.lado1 * r.lado2
  }

  // 3
  def calcularCircunferencia(figura: Figura): Double = figura match {
    case c: Circulo => 2 * Math.PI * c.radio
    case r: Rectangulo => 2 * (r.lado1 + r.lado2)
  }
  // 4
  // añadimos otra figura mas
  // tendriamos que añadir en match las nuevas y al ser sealed tendrian que ir en este fichero
}
