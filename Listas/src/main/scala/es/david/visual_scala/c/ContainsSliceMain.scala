package es.david.visual_scala.c

object ContainsSliceMain extends App {

  // def containsSlice(as: Collection[A]): Boolean
  /*
  containsSlice comprueba si la colección as se halla como subcolección de esta colección, devolviendo true en ese caso.
   */
  val list = List(1, 2, 3, 4, 5, 6, 7, 8)
  val res = list.containsSlice(List(2, 3))
  println(res)
}
