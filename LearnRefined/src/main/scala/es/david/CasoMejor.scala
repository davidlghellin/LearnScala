package es.david

import eu.timepit.refined._
import eu.timepit.refined.api.{RefType, Refined}
import eu.timepit.refined.boolean.{And, Or}
import eu.timepit.refined.char.Digit
import eu.timepit.refined.collection.{Forall, Size}
import eu.timepit.refined.numeric._

// para el casting automatico
import eu.timepit.refined.auto._

object CasoMejor extends App {

  type One = W.`1`.T
  type MinPort = W.`0`.T
  type MaxPort = W.`65535`.T
  type Abc = W.`"abc"`.T
  type Zxy = W.`"zxy"`.T

  type GitHash = String Refined And[Size[Interval.Closed[W.`4`.T, W.`40`.T]],
    Forall[Or[Digit, Interval.Closed[W.`'a'`.T, W.`'f'`.T]]]]

  type ValidPort = Int Refined Interval.Closed[MinPort, MaxPort]

  def isGitHash(s: String): Boolean =
    (s.length > 4 && s.length < 40 &&
      s.forall(c => c.isDigit || ('a' to 'f').contains(c)))

  // necesitamos el import eu.timepit.refined.auto._
  val ejemHash: GitHash = "c4943b6"
  println(ejemHash)
  val puerto: ValidPort = 55
  println(puerto)


  // lo encapsulamos en Either
  val malLadoIzquierdo: Either[String, GitHash] = RefType.applyRef[GitHash]("1")
  println(malLadoIzquierdo)

  val bienLadoDerecho:  Either[String, GitHash] = RefType.applyRef[GitHash]("c4943b6")
  println(bienLadoDerecho)

  println(malLadoIzquierdo.map(x=> x.value + "  eee"))
  println(bienLadoDerecho.map(x=> x.value + "  eee"))
}
// https://kwark.github.io/refined-in-practice
// http://fthomas.github.io/talks/2016-05-04-refined/