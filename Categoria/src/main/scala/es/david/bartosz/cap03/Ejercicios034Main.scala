package es.david.bartosz.cap03


trait MonoidBoolean[A] {
  def zero(a: A): A

  def append(a: A, b: A): A
}

object MonoidBoolean {

  def identidad[A: MonoidBoolean](a: A): A = implicitly[MonoidBoolean[A]].zero(a)

  def and[A: MonoidBoolean](a: A, b: A): A = implicitly[MonoidBoolean[A]].append(a, b)

  implicit def andBoolean = new MonoidBoolean[Boolean] {
    override def zero(a: Boolean): Boolean = a

    override def append(a: Boolean, b: Boolean): Boolean = a && b
  }

}

object Ejercicios034Main extends App {

  import MonoidBoolean._

  println(and(true, true))
  println(identidad(true))
  println(identidad(false))
  println()
  println(and(and(true, true), false))
  println(and(true, and(true, false)))
}
