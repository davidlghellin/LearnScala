package es.david.cap053

import scalaz.{Cord, EphemeralStream}
import simulacrum.{op, typeclass}

@typeclass trait Equal[F] {
  // === es mas seguro ya que solo puede compilar si son del mismo tipo
  @op("===") def equal(a1: F, a2: F): Boolean
  @op("/==") def notEqual(a1: F, a2: F): Boolean = !equal(a1, a2)
}

// ya no tenemos comparable, pero tenemos trait Order
@typeclass trait Order[F] extends Equal[F] {
  @op("?|?") def order(x: F, y: F): Ordering

  override def equal(x: F, y: F): Boolean = order(x, y) == Ordering.EQ
  @op("<" ) def lt(x: F, y: F): Boolean = ???
  @op("<=") def lte(x: F, y: F): Boolean = ???
  @op(">" ) def gt(x: F, y: F): Boolean = ???
  @op(">=") def gte(x: F, y: F): Boolean = ???

  def max(x: F, y: F): F = ???
  def min(x: F, y: F): F = ???
  def sort(x: F, y: F): (F, F) = ???
}

sealed abstract class Ordering
object Ordering {
  case object LT extends Ordering
  case object EQ extends Ordering
  case object GT extends Ordering
}
// Ademas tenemos
@typeclass trait Enum[F] extends Order[F] {
  def succ(a: F): F
  def pred(a: F): F
  def min: Option[F]
  def max: Option[F]

  @op("-+-") def succn(n: Int, a: F): F = ???
  @op("---") def predn(n: Int, a: F): F = ???

  @op("|->" ) def fromToL(from: F, to: F): List[F] = ???
  @op("|-->") def fromStepToL(from: F, step: Int, to: F): List[F] = ???
  @op("|=>" ) def fromTo(from: F, to: F): EphemeralStream[F] = ???
  @op("|==>") def fromStepTo(from: F, step: Int, to: F): EphemeralStream[F] = ???
}

// Similar al toString en java tenemos
trait Show[F] {
  def show(f: F): Cord = ???
  def shows(f: F): String = ???
}

object ObjectyThings {

}
