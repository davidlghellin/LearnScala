package es.david.visual_scala.c

object CondOptMain extends App {
  //  def condOpt[X, Y](pf: PartialFunction[X, Y]): Option[Y]

  val esPar: PartialFunction[Int, Boolean] = new PartialFunction[Int, Boolean] {
    def apply(x: Int): Boolean = true

    def isDefinedAt(x: Int) = x % 2 == 0
  }

  val res = PartialFunction.condOpt(23)(esPar)
  println(res)
}
