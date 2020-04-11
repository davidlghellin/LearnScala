package es.david.cap042

object Data402 extends App {
  // Las funciones puras se engloban típicamente en los objects
  // Sin embargo puede ser complicado y puede dar probnlemas de nombres

  /**
   * Sintactic sugar
   * Esta forma de extender es mala en tiempo de ejecución ya que cada vez que se usa
   * Crea una intermedia y luego la destruye
   *
   * @param x
   * @return
   */
  implicit def DoubleOpsMalo(x: Double): DoubleOpsMalo = new DoubleOpsMalo(x)

  class DoubleOpsMalo(x: Double) {
    def sin: Double = java.lang.Math.sin(x)
  }

  /**
   * Esta es la mejor forma
   *
   * @param x
   */
  implicit final class DoubleOps(private val x: Double) extends AnyVal {
    def mySin: Double = java.lang.Math.sin(x)
  }

  val seis: Double = 6d
  println(seis.mySin)
}

object MyApp extends App {

  import Data402._

  val seis: Double = 6d
  println(seis.mySin)
}