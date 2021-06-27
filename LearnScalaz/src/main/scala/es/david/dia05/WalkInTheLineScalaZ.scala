package es.david.dia05

import scalaz.Scalaz._
import scalaz._

object WalkInTheLineScalaZ extends App {
  type Birds = Int

  case class Pole(left: Birds, right: Birds) {
    def landLeft1(n: Birds): Pole = copy(left = left + n)

    def landRight1(n: Birds): Pole = copy(right = right + n)

    def landLeft(n: Birds): Option[Pole] =
      if (math.abs((left + n) - right) < 4) copy(left = left + n).some
      else none

    def landRight(n: Birds): Option[Pole] =
      if (math.abs(left - (right + n)) < 4) copy(right = right + n).some
      else none
  }
  println(Pole(0, 0).landLeft(2))
  println(Pole(0, 3).landLeft(10)) //none
  println()
  println(Pole(0, 0).landRight(1) flatMap {_.landLeft(2)})
  println((none: Option[Pole]) flatMap {_.landLeft(2)})

  println(Monad[Option].point(Pole(0, 0)) flatMap {_.landRight(2)} flatMap {_.landLeft(2)} flatMap {_.landRight(2)})
  println(Monad[Option].point(Pole(0, 0)) >>= {_.landRight(2)} >>= {_.landLeft(2)} >>= {_.landRight(2)})
  println(Monad[Option].point(Pole(0, 0)) >>= {_.landLeft(1)} >>= {_.landRight(4)} >>= {_.landLeft(-1)} >>= {_.landRight(-2)})
  println()
}
