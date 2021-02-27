package es.david.visual_scala.c

object ComposeMain extends App {
  def r = (l: Seq[Any]) => l.reverse

  def t = (l: Seq[Any]) => l.tail

  // se le de izquierda a derecha
  // damos dos veces la vuelta y luego tail
  val comp: Seq[Any] => Seq[Any] = t compose r compose r
  val list =List(1, 2, "three", 4, 5)
  println(comp(list))
}
