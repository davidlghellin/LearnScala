package es.david.cap042

object Data4023 extends App {
  /*
  Las instancias de Numeric(que también son instancias de Ordering) se definen como una implicit valque extiende
  la clase de tipo y puede proporcionar implementaciones optimizadas para los métodos generalizados:
   */
  import Data4022b.Foo._

  implicit val NumericDouble: Numeric[Double] = new Numeric[Double] {
    def plus(x: Double, y: Double): Double = x + y
    def times(x: Double, y: Double): Double = x * y
    def negate(x: Double): Double = -x
    def zero: Double = 0.0
    def compare(x: Double, y: Double): Int = java.lang.Double.compare(x, y)

    // optimised
    override def lt(x: Double, y: Double): Boolean = x < y
    override def gt(x: Double, y: Double): Boolean = x > y
    override def abs(x: Double): Double = java.lang.Math.abs(x)
  }

  // veamos para big decimal de java, ojo con el de scala => esta roto bigdecimal
  import java.math.{BigDecimal => BD}

  implicit val NumericBD: Numeric[BD] = new Numeric[BD] {
    def plus(x: BD, y: BD): BD = x.add(y)
    def times(x: BD, y: BD): BD = x.multiply(y)
    def negate(x: BD): BD = x.negate
    def zero: BD = BD.ZERO
    def compare(x: BD, y: BD): Int = x.compareTo(y)
  }

  // nosotros podriamos crear nuestra propia estructurea para los complejos
  /*
  // Detecta mal los tipos
  object MyComplex {
    final case class Complex[T](r: T, i: T)
    final case class ComplexInt[Int](r: Int, i: Int)

    implicit def numericComplex[T: Numeric]: Numeric[Complex[T]] =
      new Numeric[Complex[T]] {
        type CT = Complex[T]
        def plus(x: CT, y: CT) = Complex(x.r + y.r, x.i + y.i)
        def times(x: CT, y: CT): CT =
          Complex(x.r * y.r + (-x.i * y.i), x.r * y.i + x.i * y.r)
        def negate(x: CT): CT = Complex(-x.r, -x.i)
        def zero: CT = Complex(Numeric[T].zero, Numeric[T].zero)
        def compare(x: CT, y: CT): Int = {
          val real = (Numeric[T].compare(x.r, y.r))
          if (real != 0) real
          else Numeric[T].compare(x.i, y.i)
        }
      }
  }*/
}
