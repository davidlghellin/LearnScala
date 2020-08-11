package es.david.func.validation

import es.david.func.validation.BookScalaz.{Credential, FullName, UserName}
import scalaz.Scalaz._
import scalaz.{Validation, ValidationNel}

object ValidationError extends App {


  sealed trait ErrorCreadential

  case class UserNameContainsEspaces(s: String) extends ErrorCreadential

  case class UserNameEmpty(s: String) extends ErrorCreadential

  case class RealNameEmpty(s: String) extends ErrorCreadential


  type StringValidationNelError[T] = ValidationNel[ErrorCreadential, T]

  println("-------")

  def username2EEE(in: String): Validation[ErrorCreadential, UserName] =
    if (in.isEmpty) UserNameContainsEspaces("User contiene espacios").failure
    else if (in.contains(" ")) UserNameEmpty("User vacio").failure
    else UserName(in).success

  def realname2EEE(in: String): Validation[ErrorCreadential, FullName] =
    if (in.isEmpty) RealNameEmpty("RealN vacio").failure
    else FullName(in).success

  val resStringEEE = (username2EEE("david pedro").toValidationNel |@| realname2EEE("").toValidationNel) (Credential.apply)
  println(resStringEEE)
  println("-------")


}
