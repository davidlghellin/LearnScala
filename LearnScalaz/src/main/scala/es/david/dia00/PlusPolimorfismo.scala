package es.david.dia00

object PlusPolimorfismo extends App {
  /**
    * Funcion que toma una lista de A y devuelve el A
    * No importa sea cual sea ese A
    */
  def head[A](xs: List[A]): A = xs(0)

  println(head(1 :: 2 :: Nil))


  case class Car(make: String)

  println(head(Car("Civic") :: Car("CR-V") :: Nil))

  /*
   * Pensemos una funcion que suma dos valores
   */
  def plusSinImplementar[A](a1: A, a2: A): A = ???

  /*
   * Dependiendo del A tendremos que tener varias implemetaciones.
   * Una forma de lograrlo es defiendo el trait
   */
  trait PlusPoli[A] {
    def plusPoli(a2: A): A
  }
  def plusPoli[A <: PlusPoli[A]](a1: A, a2: A): A = a1.plusPoli(a2)

  /////////////////////////
  // Polimorfismo ad-hoc //
  /////////////////////////
  trait Plus[A] {
    def plus(a1: A, a2: A): A
  }
  def plus[A: Plus](a1: A, a2: A): A = implicitly[Plus[A]].plus(a1, a2)
}
