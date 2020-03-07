package es.david.typeclasses

/*
 * Quermos hacer una calculadora que implemente
 * Operaciones    Tipos
 * Suma(+)        Enteros
 * Resta(-)       Longs
 * Multi(*)       Floats
 * Division(/)    Longs
 *
 *
 * Y lo queremos hacer para poder agregar operadores o tipos
 * sin recompilar la biblioteca
 */
object Calculator extends App{

  type BinaryOp[T] = (T, T) => T

  // Operaciones conocidas al inicio del sistema
  trait Addition[T] extends BinaryOp[T]
  trait Substraction[T] extends BinaryOp[T]
  trait Multiplication[T] extends BinaryOp[T]
  trait Division[T] extends BinaryOp[T]

  def add[T: Addition](left: T, right: T) = {
    val op = implicitly[Addition[T]]
    op.apply(left, right)
  }

  def sub[T](left: T, right: T)(implicit op: Substraction[T]) = op.apply(left, right)
  def mul[T](left: T, right: T)(implicit op: Multiplication[T]) = op.apply(left, right)
  def div[T](left: T, right: T)(implicit op: Division[T]) = op.apply(left, right)

  ////////
  // implementation for ints
  object IntImplicit {

    implicit val intAdditions = new Addition[Int] {
      def apply(left: Int, right: Int) = left + right
    }
    implicit val intSub = new Substraction[Int] {
      def apply(left: Int, right: Int) = left - right
    }
    implicit val intMult = new Multiplication[Int] {
      def apply(left: Int, right: Int) = left * right
    }
    implicit val intDivision = new Division[Int] {
      def apply(left: Int, right: Int) = left / right
    }
  }
  import IntImplicit._
  println("add(3,5): " + add(3, 5))
  println("sub(3,5): " + sub(3, 5))
  println("mul(3,5): " + mul(3, 5))
  println("div(15,5): " + div(15, 5))

  // Ahora si queremos añadir para los BigDecimal
  object BigDecimalImplicit{
    // Se ve casi igual pero es porque el dominio es aritmetico
    // y es un dominio trivial
    implicit val bdAdditions = new Addition[BigDecimal] {
      def apply(left: BigDecimal, right: BigDecimal) = left + right
    }
    implicit val bdSub = new Substraction[BigDecimal] {
      def apply(left: BigDecimal, right: BigDecimal) = left - right
    }
    implicit val bdMult = new Multiplication[BigDecimal] {
      def apply(left: BigDecimal, right: BigDecimal) = left * right
    }
    implicit val bdDivision = new Division[BigDecimal] {
      def apply(left: BigDecimal, right: BigDecimal) = left / right
    }
  }

  // Ahora supongamos que queremos el modulo, esto seria lo que tenemos que agregar
  trait Remainder[T] extends BinaryOp[T]

  def rem[T](left: T, right: T)(implicit op: Remainder[T]) = op.apply(left, right)

  implicit val intRemainder = new Remainder[Int] {
    override def apply(left: Int, right: Int): Int = left % right
  }
  implicit val bdRemainder = new Remainder[BigDecimal] {
    override def apply(left: BigDecimal, right: BigDecimal): BigDecimal = left % right
  }

  println("rem(16,5): " + rem(16, 5))
}
