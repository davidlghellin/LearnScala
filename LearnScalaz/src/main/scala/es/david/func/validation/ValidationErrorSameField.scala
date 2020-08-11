package es.david.func.validation

import scalaz.Scalaz._
import scalaz._

object ValidationErrorSameField extends App {

  sealed trait ErrorDigits

  case object NotAllDigits extends ErrorDigits

  case object ToobigDigits extends ErrorDigits

  case object StillNotInts extends ErrorDigits

  def allDigits: (String) => ValidationNel[String, String] =
    s => if (s.forall(_.isDigit)) s.successNel else "Not all digits".failureNel

  def maxSizeOfTen: (String) => ValidationNel[String, String] =
    s => if (s.size <= 10) s.successNel else "Too big".failureNel

  def toInt(s: String) = try (s.toInt.right.map(_ * 100)) catch {
    case _: NumberFormatException => NonEmptyList("Still not an integer").left
  }


  type ErrorsOr[+A] = NonEmptyList[String] \/ A
  val validator = Kleisli[ErrorsOr, String, String](
    allDigits.flatMap(x => maxSizeOfTen.map(x *> _)) andThen (_.disjunction)
  )

  val integerizer = Kleisli[ErrorsOr, String, Int](toInt)
  val together = validator >>> integerizer
  println(together("aaa"))
  println(together("12345678900"))
  println(together("12345678900a"))
  println(together("123456789"))

  println()

  val validated: String => ValidationNel[String, String] = for {
    x <- allDigits
    y <- maxSizeOfTen
  } yield x *> y

  val validatedToInt = (str: String) => validated(str).map(x => toInt(x))
  println(validatedToInt("aaa"))
  println(validatedToInt("12345678900"))
  println(validatedToInt("12345678900a"))
  println(validatedToInt("12345678900a").map(x => x.getOrElse(0) + 10))
  println()
  println(validatedToInt("12"))
  println(validatedToInt("12").map(x => x.getOrElse(0) + 10))
}
