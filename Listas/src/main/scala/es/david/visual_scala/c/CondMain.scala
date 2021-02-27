package es.david.visual_scala.c

object CondMain extends App {
  //  def cond[X](x: X)(pf: PartialFunction[X, Boolean]): Boolean

  /*
  cond acepta el valor x y crea una función parcial anónima que a su vez acepta la función parcial pf,
  la aplica sobre x y devuelve el mismo valor devuelto por pf.
  Si pf no está definida para el valor de entrada x entonces la función anónima devuelve false
   */
  val esPar: PartialFunction[Int, Boolean] = new PartialFunction[Int, Boolean] {
    def apply(x: Int): Boolean = true

    def isDefinedAt(x: Int) = x % 2 == 0
  }

  val res = PartialFunction.cond(22)(esPar)
  println(res)
}
