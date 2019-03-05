package es.david.listas

object AndThenListas {
  // https://stackoverflow.com/questions/20292439/understanding-andthen


  // def andThen[C](k: (A) ⇒ C): PartialFunction[Int, C]

  val f: String => Int = s => s.length

  val g: Int => Int = i => i * 2

  val h = f andThen g

  // vamos a ver algo parecido al aggregate
  def calculaLeng(lista: List[String]): List[Int] = {
    lista.map(_.length)
  }

  def sumar(lista: List[Int]): Int = lista.foldLeft(0)(_ + _)

}