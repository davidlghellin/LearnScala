package es.david.visual_scala.f

object FilterKeysMain extends App {
  // def filterKeys(p: (K) => Boolean): Map[K, V]
  //filterKeys crea un Map con aquellos pares clave-valor cuya clave satisface el predicado p, descartando el resto.
  val map = Map(1 -> "a", 2 -> "b", 3 -> "c")
  val res = map.filterKeys(_ > 2)
  println(res)
}
