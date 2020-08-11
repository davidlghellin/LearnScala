package es.david.cap053

import scalaz.{Cord, EphemeralStream}
import simulacrum.{op, typeclass}

@typeclass trait EqualD[F] {
  // === es mas seguro ya que solo puede compilar si son del mismo tipo
  @op("===") def equal(a1: F, a2: F): Boolean
  @op("/==") def notEqual(a1: F, a2: F): Boolean = !equal(a1, a2)
}

// ya no tenemos comparable, pero tenemos trait Order
@typeclass trait OrderD[F] extends EqualD[F] {
  @op("?|?") def order(x: F, y: F): OrderingD

  override def equal(x: F, y: F): Boolean = order(x, y) == OrderingD.EQ
  @op("<") def lt(x: F, y: F): Boolean = ???
  @op("<=") def lte(x: F, y: F): Boolean = ???
  @op(">") def gt(x: F, y: F): Boolean = ???
  @op(">=") def gte(x: F, y: F): Boolean = ???

  def max(x: F, y: F): F = ???
  def min(x: F, y: F): F = ???
  def sort(x: F, y: F): (F, F) = ???
}

sealed abstract class OrderingD

object OrderingD {
  case object LT extends OrderingD
  case object EQ extends OrderingD
  case object GT extends OrderingD
}

// Ademas tenemos
@typeclass trait EnumD[F] extends OrderD[F] {
  def succ(a: F): F
  def pred(a: F): F
  def min: Option[F]
  def max: Option[F]

  @op("-+-") def succn(n: Int, a: F): F = ???
  @op("---") def predn(n: Int, a: F): F = ???
  @op("|->") def fromToL(from: F, to: F): List[F] = ???
  @op("|-->") def fromStepToL(from: F, step: Int, to: F): List[F] = ???
  @op("|=>") def fromTo(from: F, to: F): EphemeralStream[F] = ???
  @op("|==>") def fromStepTo(from: F, step: Int, to: F): EphemeralStream[F] = ???
}

// Similar al toString en java tenemos
trait Show[F] {
  def show(f: F): Cord = ???
  def shows(f: F): String = ???
}

object ObjectyThings {

}
