package es.david.visual_scala.a

object AndThenMain extends App {

  //  def andThen[Z](g: (Y) => Z): (X) => Z

  val sum1: Int => Int = (x: Int) => x + 1
  val por10: Int => Int = (x: Int) => x * 10

  // se lee de izquierda a derecha
  val suma1YPor10: Int => Int = sum1.andThen(por10)

  println(s"${suma1YPor10(1)}")
}
