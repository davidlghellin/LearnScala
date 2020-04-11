package es.david.cap042

object Data4021 extends App {

  // Definimos, el trait con una función abstracta y dos implementaciones (mayor y menor)
  trait Ordering[T] {
    def compare(x: T, y: T): Int

    def lt(x: T, y: T): Boolean = compare(x, y) < 0
    def gt(x: T, y: T): Boolean = compare(x, y) > 0
  }

  // Definimos otro trait parametrizable que extiende del anterior
  trait Numeric[T] extends Ordering[T] {
    def plus(x: T, y: T): T
    def times(x: T, y: T): T
    def negate(x: T): T
    def zero: T

    def abs(x: T): T = if (lt(x, zero)) negate(x) else x
  }

  // implementaremos una funcion de typeclass
  /*
  Ya no dependemos de la jerarquía OOP de nuestros tipos de entrada, es decir, no exigimos que nuestra entrada
  "sea un" Numeric, lo cual es de vital importancia si queremos apoyar a una clase de terceros que no podemos redefinir.

  Otra ventaja de las clases de tipos es que la asociación de la funcionalidad a los datos es en tiempo de compilación,
  en oposición al despacho dinámico de tiempo de ejecución de OOP.

  Por ejemplo, mientras que la clase List solo puede tener una implementación de un método, un método typeclass
  nos permite tener una implementación diferente dependiendo del Listcontenido y, por lo tanto, descargar el trabajo
  al tiempo de compilación en lugar de dejarlo en tiempo de ejecución.
   */
  def signOfTheTimes[T](t: T)(implicit N: Numeric[T]): T = {
    import N._
    times(negate(abs(t)), t)
  }
  class MyNumeric extends Numeric[Int]{
    override def plus(x: Int, y: Int): Int = x + y

    override def times(x: Int, y: Int): Int = x ^ y

    override def negate(x: Int): Int = -x

    override def zero: Int = 0

    override def compare(x: Int, y: Int): Int = ???
  }
}
