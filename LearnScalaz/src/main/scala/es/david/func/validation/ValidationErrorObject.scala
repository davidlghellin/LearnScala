package es.david.func.validation

import scalaz.Scalaz._
import scalaz.Validation

object ValidationErrorObject extends App {


  final case class UserName(value: String) extends AnyVal

  final case class FullName(value: String) extends AnyVal

  final case class Credential(user: UserName, name: FullName)


  sealed trait ErrorCreadentialObj

  final case object UserNameContainsEspacesObj extends ErrorCreadentialObj

  final case object UserNameEmptyObj extends ErrorCreadentialObj

  final case object UserNameLowerObj extends ErrorCreadentialObj

  final case object UserNameSizeMObj extends ErrorCreadentialObj

  final case object RealNameEmptyObj extends ErrorCreadentialObj


  def username2Obj(in: String): Validation[ErrorCreadentialObj, UserName] =
    if (in.isEmpty) UserNameEmptyObj.failure
    else if (in.contains(" ")) UserNameContainsEspacesObj.failure
    else UserName(in).success

  def userBeginUpper(in: String): Validation[ErrorCreadentialObj, UserName] =
    if (in(0).isUpper) UserNameLowerObj.failure
    else UserName(in).success

  def userLength(in: String): Validation[ErrorCreadentialObj, UserName] =
    if (in.size > 5) UserNameSizeMObj.failure
    else UserName(in).success


  def realname2Obj(in: String): Validation[ErrorCreadentialObj, FullName] =
    if (in.isEmpty) RealNameEmptyObj.failure
    else FullName(in).success

  val david: String = "david pedro"
  val resUser = (username2Obj(david).toValidationNel *> userBeginUpper(david).toValidationNel)
  println(resUser)


  val res = (username2Obj(david).toValidationNel |@| realname2Obj("").toValidationNel) (Credential.apply)
  println(res)

  val ress = (username2Obj(david).toValidationNel |@| realname2Obj(david).toValidationNel) ((_, _) => Credential)
  println(ress)

  // Comprueba los dos casos para poder crear credential
  println((username2Obj("David").toValidationNel |@| realname2Obj("Lopez").toValidationNel) (Credential.apply))
}
