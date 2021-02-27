package es.david.visual_scala.f

object FillMain extends App {
  // def fill[A](n1: Int, ..., nm: Int)(a: => A): Collection[ ... Collection[A] ... ]
  // fill crea una colección n-dimensional conteniendo el resultado de cierta computación a
  val list = List(1 ,2)
  val listFill= List.fill(3)(list)
  // repite 3 veces List(1,2) dentro de otra lista
  println(listFill)
}
