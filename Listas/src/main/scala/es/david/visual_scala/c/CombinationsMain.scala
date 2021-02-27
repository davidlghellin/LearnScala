package es.david.visual_scala.c

object CombinationsMain extends App {
  //def combinations(k: Int): Iterator[Collection[A]]
  /*
   combinations computa todas las posibles combinaciones de elementos de esta colección
   tomados de k en k y devuelve un Iterator para iterarlos.
   */
  val list: List[Int] = List(1, 1, 1, 2, 3)
  val res: Iterator[List[Int]] = list.combinations(2)

  res.foreach(println)
  "abbbc".combinations(2).foreach(println)
}
