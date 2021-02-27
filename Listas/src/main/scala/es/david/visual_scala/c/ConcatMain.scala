package es.david.visual_scala.c

object ConcatMain extends App {
  // def concat[A](as: Collection[A]*): Collection[A]
  /*
  concat acepta un número indeterminado de colecciones y utiliza sus elementos para crear una única colección.
   */
  val list = List(1, 2, 3)
  val res = List.concat(list, list, list)

}
