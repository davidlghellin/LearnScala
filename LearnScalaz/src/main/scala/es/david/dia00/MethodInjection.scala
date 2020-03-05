package es.david.dia00

import es.david.dia00.Suma.MonoidFinal

object MethodInjection extends App {
  //  Si tuviéramos que escribir una función que sume dos tipos usando el Monoid, debemos llamarlo así.
  def plusA[A: MonoidFinal](a: A, b: A): A = implicitly[MonoidFinal[A]].mappend(a, b)

  def plusB[A: MonoidFinal](a: A, b: A): A = {
    val x = implicitly[MonoidFinal[A]]
    x.mappend(a, b)
  }

  // queremos agregar un operador para todos los tipos
  trait MonoidOp[A] {
    val F: MonoidFinal[A] // Obtenemos instancia de monoide
    val value: A // Creamos la variable
    def |+|(a2: A) = F.mappend(value, a2) // combinamos el valor y el parametro
  }

  /**
    * Creamos la funcion donde tenemos el implicito el monoide
    */
  implicit def toMonoidOp[A: MonoidFinal](a: A): MonoidOp[A] = new MonoidOp[A] {
    val F = implicitly[MonoidFinal[A]]
    val value = a
  }

  implicit val intMonoid = MonoidFinal.IntMonoid
  println(3 |+| 4)

  implicit val strMonoid = MonoidFinal.StringMonoid
  println("a" |+| "b")

  // Hemos creado nuestro propio operador

  // de Scalaz tenemos cosas de Option y boolean
  import scalaz.Scalaz._

  println(1.some | 2)
  println(Some(1).getOrElse(2))

  println((1 > 10) ? 1 | 2)
  println(if (1 > 10) 1 else 2)
}
