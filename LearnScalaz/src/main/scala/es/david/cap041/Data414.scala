package es.david.cap041

import es.david.cap041.Data410.{A, ABC, B, C}

/**
 * Alernativa a Prodcuto y coproducto
 */
object Data414 extends App {

  /*
   * Otra forma de producto es una tupla, que es como el final case class, pero no queda claro y tiene peor performance
   */
  val abc: ABC = ABC(A, "", 6)
  println(abc)
  val abcTupla: (A.type, B, C) = (A, "dos", 3)
  println(abcTupla)

  /*
   * Otra forma del coproducto es usar Either ...
   */
  sealed abstract class AAA
  case object X extends AAA
  case object Y extends AAA
  case object Z extends AAA
  //  X |: Y |:Z
  val x: String |: Int = Right(1)
  println(x)

  type Error = String
  type Success = String

  def call(url:String): Either[Error,Success]={
    if (url.contains("www"))
      Right(url)
    else Left("Direccion no valida")
  }
  println(call("www.google.es"))
  println(call("google.es"))

  // Copodructo
  type |:[L, R] = Either[L, R]
  type Accept = String |: Int |: Boolean
  val accept1: Accept = Right(Right(true))
  val accept2: Accept = Right(Left(2))
  val accept3: Accept = Left(Left(2).toString)
  println(accept1)
  println(accept2.map(_ + "333"))
  println(accept3)

  sealed abstract class Accepted
  final case class AcceptString(value: String) extends Accepted
  final case class AcceptInt(value: Int) extends Accepted
  final case class AcceptBoolean(value: Boolean) extends Accepted


  // https://github.com/frees-io/iota
  // a coproduct of types using scala.util.Either
  // https://github.com/frees-io/iota/blob/master/docs/scalaz.md
  type EitherFoo = Either[Int, Either[String, Double]]
  type Either1 = Either[String, Double]
  type Either2 = Either[Int, Either1]
  val ei1: Either1 = Right(2d)
  val ei1b: Either1 = Left("dd")
  println(ei1)
  println(ei1b)
  val eiFoo: EitherFoo = Right(Right(2d))
  println(eiFoo)
}
