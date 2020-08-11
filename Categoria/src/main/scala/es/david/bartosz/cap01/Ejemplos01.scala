package es.david.bartosz.cap01

object Ejemplos01 extends App {
  // composicion
  def m1(n: Int): Int = n + 1

  val f1: Int => Int = (n: Int) => n + 1
  val f2: Int => Double = (n: Int) => n * 10d
  val f3: Double => String = (n: Double) => n.toString

  val fCompose1: Int => Double = f2 compose f1
  val fcompose1Final: Int => String = f3 compose fCompose1

  val fCompose2: Int => String = f3 compose f2
  val fCompose2Final: Int => String = fCompose2 compose f1

  println(fcompose1Final(2))
  println(fCompose2Final(2))

  // elemento identidad
  def identidad[A](a: A): A = a

  def myId[A]: A => A = (a: A) => a

  // composicion de identidad
  println(identidad[Int](2))


  val fIdentidad: Int => Int = myId[Int]

  val idenCompose1: Int => Int = f1 compose fIdentidad
  val idenCompose2: Int => Int = fIdentidad compose f1

  println(idenCompose1(2))
  println(idenCompose2(2))
  println(assert(idenCompose1(2) == idenCompose2(2)))
}
