package es.david.visual_scala.c

object CorrespondsMain extends App {
  // def corresponds[B](bs: Collection[B])(p: (A, B) => Boolean): Boolean
  //https://www.garysieling.com/blog/scala-corresponds-example/
  val list = List(1, 2, 3, 4)
  val listaEmparejada = List((1, 4), (2, 5), (3, 7), (3, 11))
  val cmp: (Int, (Int, Int)) => Boolean =
    (k: Int, v: (Int, Int)) => {
      val (inicio, fin) = v
      k >= inicio && k < fin
    }
  /*
  Para la cada par, comprueba que se cumplen las condiciones
   */
  val resT = list.corresponds(listaEmparejada)(cmp)
  println(resT)
}
