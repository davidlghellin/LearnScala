package es.david.dia0

object Ejem02FoldLeft extends App {

  /**
   * Lo que queremos es una función que generalizara List.
   * Así que queremos generalizar sobre la operación foldLeft
   */
  object FoldLeftList {
    def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B) = xs.foldLeft(b)(f)
  }

  trait Monoid[A] {
    def mappend(a1: A, a2: A): A

    def mzero: A
  }

  def sum[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    FoldLeftList.foldLeft(xs, m.mzero, m.mappend)
  }

  object IntMonoid extends Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a + b

    def mzero: Int = 0
  }

  implicit val intMonoid = IntMonoid
  println(sum(List(1, 2, 3, 4)))

  implicit val StringMonoid: Monoid[String] = new Monoid[String] {
    def mappend(a: String, b: String): String = a + b

    def mzero: String = ""
  }
  println(sum(List("a", "b", "c")))

  val multiMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a * b
    def mzero: Int = 1
  }
  println(sum(List(1, 2, 3, 4))(multiMonoid))

  /////////
  //Ahora podemos aplicar la misma abstracción para extraer la FoldLeft clase de tipo.
  // La F es una clase de tipo, Constructor de tipo
  trait FoldLeft[F[_]] {
    def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B
  }
  object FoldLeft {
    implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
      def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B) = xs.foldLeft(b)(f)
    }
  }

  def sumDeTipo[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val m = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }
  println(sumDeTipo(List(1, 2, 3, 40)))
}
