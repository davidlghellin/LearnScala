package es.david.cap057

import scalaz.Scalaz._
import scalaz._

sealed trait MyErr

case object E1 extends MyErr

case object E2 extends MyErr

object AplicativeMain extends App {
  val o1: Option[Int] = Some(100)
  val o2: Option[Int] = None
  val o3: Option[Int] = Some(200)

  val v1: \/[String, Int] = \/-(100)
  val v2: \/[String, Int] = -\/("error")
  val v3: \/[String, Int] = \/-(200)
  println(v1.map(_ + 100))
  println(v2.map(_ + 100))

  case class Clase(a: Int, b: Int)

  println((v1 |@| v3) (Clase))
  println((o1 |@| o3) (Clase))
  println((v1 |@| v2) (Clase))
  println((o1 |@| o2) (Clase))

  def suma(a: Int, b: Int): Int = a + b

  println((v1 |@| v3) (suma))
  println((o1 |@| o3) (suma))
  println((v1 |@| v2) (suma))
  println((o1 |@| o2) (suma))


  //////
  println()
  val ve1: \/[MyErr, Int] = \/-(100)
  val ve21: \/[MyErr, Int] = -\/(E1)
  val ve22: \/[MyErr, Int] = -\/(E2)
  val ve3: \/[MyErr, Int] = \/-(200)
  println(ve1.map(_ + 100))
  println(ve22.map(_ + 100))
  println((ve1 |@| ve3) (Clase))
  println((ve1 |@| ve21) (Clase))
  println((ve1 |@| ve22) (Clase))
}
