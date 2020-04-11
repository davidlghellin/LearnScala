package es.david.jrwest

object Tipos extends App{

  // vamos a definiir un equals
  trait Equal[-A] {
    def equal(a1: A, a2: A): Boolean
  }

  object IntEqual extends Equal[Int] {
   def equal(a1: Int, a2: Int): Boolean = a1 == a2
   }
  println(IntEqual.equal(1,2))
  //println(IntEqual.equal(1,"3"))//no podemos porque no es del mismo tipo

  // imagina que tenemos una subclase de Int
  // podriamos comparar ya ue es un subtipo
  case class IntA(a:Int)
  object IntAEqual extends Equal[IntA] {
    def equal(a1: IntA, a2: IntA): Boolean = a1 == a2
  }
  class IntB(a:Int) extends IntA(a)
  val a=IntA(1)
  val b=IntA(1)
  println(IntEqual.equal(a.a,b.a))

}
