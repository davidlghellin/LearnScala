package es.david.cap042

import simulacrum.{op, typeclass}

object Data4022 extends App {
  // La sintaxis para escribir signOfTheTimeses torpe, hay algunas cosas que podemos hacer para limpiarlo.
  trait Ordering[T] {
    def compare(x: T, y: T): Int

    def lt(x: T, y: T): Boolean = compare(x, y) < 0
    def gt(x: T, y: T): Boolean = compare(x, y) > 0
  }

  trait Numeric[T] extends Ordering[T] {
    def plus(x: T, y: T): T
    def times(x: T, y: T): T
    def negate(x: T): T
    def zero: T

    def abs(x: T): T = if (lt(x, zero)) negate(x) else x
  }
  object Numeric {
    def apply[T](implicit numeric: Numeric[T]): Numeric[T] = numeric
  }

  //veamos el metodo con ruido,
  // estamos pasando el implicito
  def signOfTheTimesRuido[T](t: T)(implicit N: Numeric[T]): T = {
    import N._
    times(negate(abs(t)), t)
  }
  // Podemos obtener el metodo con menos ruido,
  // hemos quitado el implicito y al parametro de tipo estamos especificando
  def signOfTheTimes[T: Numeric](t: T): T = {
    val N = Numeric[T]
    import N._
    times(negate(abs(t)), t)
  }
  // pero aun es malo para nosotros como implementadores.

  object NumericNew {
    def apply[T](implicit numeric: Numeric[T]): Numeric[T] = numeric

    object ops {
      implicit class NumericOps[T](t: T)(implicit N: Numeric[T]) {
        def +(o: T): T = N.plus(t, o)
        def *(o: T): T = N.times(t, o)
        def unary_- : T = N.negate(t)
        def abs: T = N.abs(t)

        // duplicated from Ordering.ops
        def <(o: T) = N.lt(t, o)
        def >(o: T) = N.gt(t, o)
      }
    }
  }
  import NumericNew.ops._
  def signOfTheTimesNew[T: Numeric](t: T): T = -(t.abs) * t

}

object Data4022b extends App {
  // hemos puesto en el object lo referente a las anotaciones por que detectaba error de op
  object Foo {
    @typeclass trait Ordering[T] {
      def compare(x: T, y: T): Int
      @op("<") def lt(x: T, y: T): Boolean = compare(x, y) < 0
      @op(">") def gt(x: T, y: T): Boolean = compare(x, y) > 0
    }

    @typeclass trait Numeric[T] extends Ordering[T] {
      @op("+") def plus(x: T, y: T): T
      @op("*") def times(x: T, y: T): T
      @op("unary_-") def negate(x: T): T
      def zero: T
      def abs(x: T): T = if (lt(x, zero)) negate(x) else x
    }

    import Foo.Numeric.ops._
    def signOfTheTimes[T: Numeric](t: T): T = (t.abs) * t
  }
}
