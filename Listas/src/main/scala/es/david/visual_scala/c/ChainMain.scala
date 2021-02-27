package es.david.visual_scala.c

// https://superruzafa.github.io/visual-scala-reference/es/chain
object ChainMain extends App {
  //  def chain[X](fs: Collection[(X) => X]): (X) => X
  /*
  chain crea una función anónima que acepta un valor y
  devuelve el resultado de aplicar secuencialmente todas las funciones contenidas en la colección fs
   */
  def r = (l: Seq[Any]) => l.reverse

  def t = (l: Seq[Any]) => l.tail

  val resultadoChain = (Function.chain(List(r, t, t))) (List(1, 2, "three", 4, 5))
  // aplica r
  // aplica t
  // aplica t
  println(resultadoChain)

  val result: Seq[Any] => Seq[Any] = r andThen t andThen t
  val resultAndThens = result(List(1, 2, "three", 4, 5))
  println(resultAndThens)

}
