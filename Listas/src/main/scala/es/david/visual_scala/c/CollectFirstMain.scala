package es.david.visual_scala.c


object CollectFirstMain extends App {
  val listInt = List(1, 1, 1, 1, 2, 3, 4, 5)

  /*
  collectFirst aplica la función parcial f al primer elemento de esta colección para el cual f está definida
  y devuelve su resultado envuelto con Some.
  Si la función parcial f no está definida para ningún elemento de esta colección entonces se devuelve None.
   */
  val esPar: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
    def apply(x: Int): Int = x * 10

    def isDefinedAt(x: Int) = x % 2 == 0
  }

  val listaPares = listInt.collectFirst(esPar)
  println(listaPares)
}
