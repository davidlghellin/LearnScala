package es.david.dia00
// http://eed3si9n.com/learning-scalaz/sum+function.html

object Suma extends App {
  /**
    * Veamos las definiciones de una suma de mas especifica a mas general
    */
  def sum1(xs: List[Int]): Int = xs.foldLeft(0) { _ + _ }
  println(sum1(List(1, 2, 3, 4)))

  /////////////////////////
  //       Monoide       //
  /////////////////////////
  /*
   * Un monoide es "algo" que contiene una funcion para combinar y el elemento neutro
   */
  object IntMonoidI {
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }

  /**
    * Aplicamos los cambios para que la suma use lo que hemos creado
    * El 0       -> IntMonoidI.mzero
    * La funcion -> IntMonoidI.mappend
    */
  def sumIntMonoid(xs: List[Int]): Int = xs.foldLeft(IntMonoidI.mzero)(IntMonoidI.mappend)
  println(sumIntMonoid(List(1, 2, 3, 4)))

  /*
   * Este Monoid lo generalizaremos para que cualquier tipo pueda usarlo
   */
  trait Monoid[A] {
    def mappend(a1: A, a2: A): A
    def mzero: A
  }

  /**
    * Creamos el objeto especifico para este tipo
    */
  object IntMonoid extends Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }
  def sumInt(xs: List[Int], m: Monoid[Int]): Int = xs.foldLeft(m.mzero)(m.mappend)
  println(sumInt(List(1, 2, 3, 4), IntMonoid))

  // en la definicion de la suma no estamos usando el Int
  // por lo que lo generalizaremos

  def sum[A](xs: List[A], m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)
  println(sum(List(1, 2, 3, 4), IntMonoid))

  // Ahora queremos hacer que el propio Monoide se pase implicitamente
  // Curryficamos la funcion y añadiendo el implicit lo tenemos
  def sumImplicit[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)
  // Para poder usarlo creamos el implito
  implicit val intMonoid = IntMonoid
//  println(sumImplicit(List(1, 2, 3, 4)))

  // Otra forma es poner el implicito vinculado al context bound y no pasarlo como parametro
  // https://docs.scala-lang.org/tutorials/FAQ/context-bounds.html
  // https://gvolpe.github.io/blog/context-bound-vs-implicit-evidence/
  def sumImpliVinculado[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }
  println(sumImpliVinculado(List(1, 2, 3, 4)))

  // Crearemos el monoide de String y enteros
  trait MonoidFinal[A] {
    def mappend(a1: A, a2: A): A
    def mzero: A
  }
  object MonoidFinal {
    implicit val IntMonoid: MonoidFinal[Int] = new MonoidFinal[Int] {
      def mappend(a: Int, b: Int): Int = a + b
      def mzero: Int = 0
    }
    implicit val StringMonoid: MonoidFinal[String] = new MonoidFinal[String] {
      def mappend(a: String, b: String): String = a + b
      def mzero: String = ""
    }
    // Podriamos crear uno con la multiplicacion
    implicit val IntMonoid2: MonoidFinal[Int] = new MonoidFinal[Int] {
      def mappend(a: Int, b: Int): Int = a * b
      def mzero: Int = 1
    }
  }

  def sum[A: MonoidFinal](xs: List[A]): A = {
    val m = implicitly[MonoidFinal[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }
  implicit val intMonoidFinal = MonoidFinal.IntMonoid2
  println(sum(List(1, 2, 3, 4)))
}
