package es.david.visual_scala.a

// https://superruzafa.github.io/visual-scala-reference/es/appended
object AppendedMain extends App {
  //  def appended[B >: A](b: B): Collection[B]
  //  def :+[B >: A](b: B): Collection[B]

  // crea una copia añadiendo al final
  val seq = Seq(1, 2, 3, 4)
  val listaDos = 1 :: 2 :: Nil
  val nuevo: Int = 100
  val result: Seq[Int] = seq :+ nuevo

  val list: List[Int] = seq.toList
  val resultList: List[Int] = list :+ nuevo
}
