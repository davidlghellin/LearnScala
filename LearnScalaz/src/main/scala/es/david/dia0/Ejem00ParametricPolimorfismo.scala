package es.david.dia0

// http://eed3si9n.com/learning-scalaz/
object Ejem00ParametricPolimorfismo extends App {
  // Parametric polymorphism
  def cabecera[A](xs: List[A]): A = xs(0)
  println(cabecera(1 :: 2 :: Nil))

  case class Coche(marca: String)
  println(cabecera(Coche("Civic") :: Coche("CR-V") :: Nil))

  ////////
  // Subtype polymorphism
  // vamos a pensar una funcion suma que dependiendo del tipo haga una cosa u otra
  def plus2[A](a1: A, a2: A): A = ???

  trait Plus[A] {
    def plus(a2: A): A
  }

  case class Numero(n: Int) extends Plus[Numero] {
    override def plus(a2: Numero): Numero = Numero(this.n + a2.n)
  }

  def plusContra[A <: Plus[A]](a1: A, a2: A): A = a1.plus(a2)

  val n1 = Numero(1)
  val n2 = Numero(2)
  println(n1.plus(n2))
  println(plusContra(n1,n2))

  // Polimorfismo ad-hoc
  trait PlusAddHoc[A] {
    def plus(a1: A, a2: A): A
  }
   def plus[A: PlusAddHoc](a1: A, a2: A): A = implicitly[PlusAddHoc[A]].plus(a1, a2)

  case class NumeroAddHoc(n: Int) extends PlusAddHoc[NumeroAddHoc] {
    override def plus(a1: NumeroAddHoc, a2: NumeroAddHoc): NumeroAddHoc = NumeroAddHoc(a1.n + a2.n)
  }

  // no me ha quedado claro este implicit
  implicit object NumeroAddHocImplicit extends PlusAddHoc[NumeroAddHoc] {
    override def plus(a1: NumeroAddHoc, a2: NumeroAddHoc): NumeroAddHoc = NumeroAddHoc(a1.n + a2.n)
  }

  val numeroAddHoc1: NumeroAddHoc = NumeroAddHoc(1)
  val numeroAddHoc2: NumeroAddHoc = NumeroAddHoc(2)
  println(plus(numeroAddHoc1,numeroAddHoc2))
}
