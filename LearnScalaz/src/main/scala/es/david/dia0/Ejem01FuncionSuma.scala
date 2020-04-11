package es.david.dia0

// http://eed3si9n.com/learning-scalaz/
object Ejem01FuncionSuma extends App {
  // funcion suma listas de enteros
  def sumEnteros(xs: List[Int]): Int = xs.foldLeft(0)(_ + _)
  println(sumEnteros(1 :: 2 :: 3 :: Nil))

  // Monoide
  //Es un tipo para el que existe una función mappend,
  //que produce otro tipo en el mismo conjunto;
  // y también una función que produce un cero.
  object IntMonoidInt {
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }
  // usando el monoide de entero
  def sumM(xs: List[Int]): Int = xs.foldLeft(IntMonoidInt.mzero)(IntMonoidInt.mappend)
  println(sumM(1 :: 2 :: 3 :: Nil))

  // generalizamos y creamos el monoide
  trait Monoid[A] {
    def mappend(a1: A, a2: A): A
    def mzero: A
  }
  // creamos un monoide de enteros
  object IntMonoid extends Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }
  def sum(xs: List[Int], m: Monoid[Int]): Int = xs.foldLeft(m.mzero)(m.mappend)
  println(sum(1 :: 2 :: 3 :: Nil,IntMonoid))
  // podemos hacer la suma anterior más general, parametrizando
  def sumParametrico[A](xs: List[A], m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)
  println(sumParametrico(1 :: 2 :: 3 :: Nil,IntMonoid))

  // lo siguiente es hacer implicito esto para no pasar el monoid
  def sumImplicit[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)
  implicit val intMonoid = IntMonoid
  println(sumImplicit(1 :: 2 :: 3 :: Nil))

  // a menudo se escribe el implicito como vinculado
  // es meter dentro el implicit en vez de pasarlo por parametro curryficado
  // de todas formas necesitamos el implicito
  def sumImplicitVinculado[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }

  println(s"solucion : ${sumImplicitVinculado(List(1, 2, 3, 4))}")
  // veamos esto creando un monoid de string añadiendo a la suma algún caracter más
  object StringMonoid extends Monoid[String] {
    def mappend(a: String, b: String): String = a + b + "_string_"
    def mzero: String = ""
  }
  implicit val strMonoid = StringMonoid
  println(s"solucion : ${sumImplicitVinculado(List("a","b","c"))}")

  // veamos esto en una solucion del siguiente object
}

object Ejemp01b extends App {
  trait Monoid[A] {
    def mappend(a1: A, a2: A): A
    def mzero: A
  }
  object Monoid {
    implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
      def mappend(a: Int, b: Int): Int = a + b
      def mzero: Int = 0
    }
    implicit val StringMonoid: Monoid[String] = new Monoid[String] {
      def mappend(a: String, b: String): String = a + b
      def mzero: String = ""
    }
  }
  def sum[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }
  println(sum(List("a", "b", "c")))

  // nuevos monoides
  val multiMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a * b
    def mzero: Int = 1
  }
  // aunq estemos usando la suma estamos pasando un nuevo monoide
  // que redefine el mapped y por eso se comporta con la multiplicacion
  // ya que la funcion sum, espera un monoide
  println(sum(List(1, 2, 3, 4))(multiMonoid))
}