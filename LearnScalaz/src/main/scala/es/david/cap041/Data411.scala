package es.david.cap041

/**
 * Recusive ADTs
 */
object Data411 extends App{
  /*
   * Cuando un ADT se refiere a si mismo, lo llamamos Recusive Algebraic Data Type
   */

  /*
   * scalaz.IList es una altenativa a List de la librería estantadar de Scala
   * Es recursiva porque ICons contiene una referencia a IList
   */
  // https://github.com/scalaz/scalaz/blob/series/7.3.x/core/src/main/scala/scalaz/IList.scala
  sealed abstract class IList[A]
  final case class INil[A]() extends IList[A]
  final case class ICons[A](head: A, tail: IList[A]) extends IList[A]
}
