package es.david.bartosz.cap02

// TODO
object Ejercicios022 extends App {
  def mRandom(): Int = {
    Math.random().toInt
  }

  val fRandom: Unit => Int = {
    (_: Unit) => Math.random().toInt
  }

  val a = fRandom
  println(a)
  println(a)


  val a2 = fRandom
  val res = Ejercicios021.Memoize1(a2)
  println(res)
}
