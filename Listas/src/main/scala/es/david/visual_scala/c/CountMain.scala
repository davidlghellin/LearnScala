package es.david.visual_scala.c

object CountMain extends App {
  //count determina cuántos elementos de esta colección satisfacen el predicado p.
  val list = List(1, 2, 3, 4, 5)
  val nCount = list.count(x => x % 2 == 0)
  println(nCount)
}
