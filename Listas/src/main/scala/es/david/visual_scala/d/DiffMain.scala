package es.david.visual_scala.d

object DiffMain extends App {
  // diff computa la diferencia multiconjunto entre esta colección y la colección as.
  // left anti join
  val l1 = List(1, 2, 3, 4, 5, 6)
  val l2 = List(5, 6, 7, 8, 9, 0)
  val res1 = l1.diff(l2)
  println(res1)
}
