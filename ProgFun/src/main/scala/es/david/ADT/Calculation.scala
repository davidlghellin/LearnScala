package es.david.ADT

sealed trait Calculation

final case class Literal(v: Double) extends Calculation
final case class Add(a: Calculation, b: Calculation) extends Calculation
final case class Subtract(a: Calculation, b: Calculation) extends Calculation
final case class Multiply(a: Calculation, b: Calculation) extends Calculation
final case class Divide(a: Calculation, b: Calculation) extends Calculation

object Calculation {

  def eval(c: Calculation): Double =
    c match {
      case Literal(v) => v
      case Add(a, b) => eval(a) + eval(b)
      case Subtract(a, b) => eval(a) - eval(b)
      case Multiply(a, b) => eval(a) * eval(b)
      case Divide(a, b) => eval(a) / eval(b)
    }

  def prettyPrint(c: Calculation): String =
    c match {
      case Literal(v) => v.toString
      case Add(a, b) => s"${prettyPrint(a)} + ${prettyPrint(b)}"
      case Subtract(a, b) => s"${prettyPrint(a)} - ${prettyPrint(b)}"
      case Multiply(a, b) => s"${prettyPrint(a)} * ${prettyPrint(b)}"
      case Divide(a, b) => s"${prettyPrint(a)} / ${prettyPrint(b)}"
    }

  /*
  FIXME:
  tenemos que usar todos los rasgos del sealed, para que no de error
  si por un casual quisieramos poner por ejemplo una excepcion
  usar la notacion (c: @unchecked) match

  No recomendable ya que no estamos definiendo bien
   */
}

object Main extends App {

  val uno: Calculation = Literal(1)
  val dos: Calculation = Literal(2)

  val result: Add = Add(uno, dos)

  println(Calculation.eval(result))
  println(Calculation.prettyPrint(result))

}