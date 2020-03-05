package es.david.dia00

import es.david.dia00.FoldLeft.sum
import es.david.dia00.Suma.MonoidFinal

object FoldLeft extends App {

  object FoldLeftList {
    def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B) = xs.foldLeft(b)(f)
  }

  /**
    * Definimos nuestro propio sum usando el foldLeft
    *
    * El primer corchete es la forma de decir que es implito ese tipo
    * Luego tenemos que decir el implicity
    *
    * Usamos el foldLeft antes creado pasando:
    * - la lista
    * - el elemento neutro del monoide
    * - la forma de combinar del monoide
    */
  def sumFoldLeftList[A: MonoidFinal](xs: List[A]): A = {
    val m = implicitly[MonoidFinal[A]]
    FoldLeftList.foldLeft(xs, m.mzero, m.mappend)
  }

  implicit val intMonoid = MonoidFinal.IntMonoid
  println(sumFoldLeftList(List(1, 2, 3, 4)))
  implicit val strMonoid = MonoidFinal.StringMonoid
  println(sumFoldLeftList(List("a", "b", "c")))

  /////////////////////////
  // Nos abastraemos en FL
  /////////////////////////
  /**
    * Creamos el trait añadiendo un nuevo elemento entre corchetes
    * Ahora nos da igual que sea lista u otra cosa
    * @tparam F
    */
  trait FoldLeft[F[_]] {
    def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B
  }

  /**
    * Ya podemos crear la instancia implita que lo soluciona
    */
  object FoldLeft {
    implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
      def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B) = xs.foldLeft(b)(f)
    }
  }

  /**
    * Hacemos nuestro propia suma, teniendo en cuenta que esta vez necesitamos dos implicitos
    * El de la coleccion
    * El del monoide
    *
    * Necesitamos hacer los implicitos vinculados
    *
    * Luego ya es pasar los parametros a la funcion
    * - la lista
    * - el elemento neutro del monoide
    * - la forma de combinar del monoide
    */
  def sum[M[_]: FoldLeft, A: MonoidFinal](xs: M[A]): A = {
    val m = implicitly[MonoidFinal[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }
  // No hacemos los implicitos del monoide porque los tenemos ya creados antes
  println(sum(List(1, 2, 3, 4)))
  println(sum(List("a", "b", "c")))
}