package es.david.dia06

import scalaz.Scalaz._
import scalaz.Writer

object WriterScalaZ extends App {
  def logNumber(x: Int): Writer[List[String], Int] =
    x.set(List("Got number: " + x.shows))

  def multWithLog: Writer[List[String], Int] = for {
    a <- logNumber(3)
    _ <- logNumber(4)
    b <- logNumber(5)
  } yield a * b

  println(multWithLog.run)
  println(multWithLog.run._1)
  println(multWithLog.run._2)

  println()

  //max comun divisor
  def gcd(a: Int, b: Int): Writer[List[String], Int] =
    if (b == 0) for {
      _ <- List("Finished with " + a.shows).tell
    } yield a
    else {
      List(a.shows + " mod " + b.shows + " = " + (a % b).shows).tell >>= { _ =>
        gcd(b, a % b)
      }
    }

  println(gcd(8, 3).run)

  // ojo con usar las listas, el apend de los monoides es lento, usar Vector

}
