package es.david.func.validation

import scalaz.Scalaz._
import scalaz.{NonEmptyList, Validation, ValidationNel, \/}

object BookScalaz extends App {

  final case class UserName(value: String) extends AnyVal

  final case class FullName(value: String) extends AnyVal

  final case class Credential(user: UserName, name: FullName)

  def username(in: String): String \/ UserName =
    if (in.isEmpty) "emty usernema".left
    else if (in.contains(" ")) "username contains espaces".left
    else UserName(in).right

  def realname(in: String): String \/ FullName =
    if (in.isEmpty) "empty real name".left
    else FullName(in).right

  // solo nos dara el primer error
  val resDisjunction: String \/ Credential = for {
    u <- username("david pedro")
    r <- realname("")
  } yield (Credential(u, r))
  println(resDisjunction)

  val resDisjunction2 = (username("david pedro") |@| realname("")) (Credential.apply)
  println(resDisjunction2)

  ////// todos
  def username2(in: String): ValidationNel[String, UserName] =
    if (in.isEmpty) "emty usernema".failureNel
    else if (in.contains(" ")) "username contains espaces".failureNel
    else UserName(in).success

  def realname2(in: String): ValidationNel[String, FullName] =
    if (in.isEmpty) "empty real name".failureNel
    else FullName(in).success

  val resTodosErrores = (username2("david pedro") |@| realname2("")) (Credential.apply)
  println(resTodosErrores)

  // ValidationTypeString
  type StringValidationNel[T] = ValidationNel[String, T]

  def usernameStringNel(in: String): StringValidationNel[UserName] =
    if (in.isEmpty) "emty usernema".failureNel
    else if (in.contains(" ")) "username contains espaces".failureNel
    else UserName(in).success

  def realnameStringNel(in: String): StringValidationNel[FullName] =
    if (in.isEmpty) "empty real name".failureNel
    else FullName(in).success

  val resStringNel = (usernameStringNel("david pedro") |@| realnameStringNel("")) (Credential.apply)
  println(resStringNel)


  type StringValidation[T] = Validation[String, T]

  def usernameString(in: String): StringValidation[UserName] =
    if (in.isEmpty) "emty usernema".failure
    else if (in.contains(" ")) "username contains espaces".failure
    else UserName(in).success

  def realnameString(in: String): StringValidation[FullName] =
    if (in.isEmpty) "empty real name".failure
    else FullName(in).success

  val resString = (usernameString("david pedro") |@| realnameString("")) (Credential.apply)
  println(resString)


  sealed trait ErrorCreadential

  case class UserNameContainsEspaces(s:String) extends ErrorCreadential

  case class UserNameEmpty(s:String) extends ErrorCreadential

  case class RealNameEmpty(s:String) extends ErrorCreadential



  type StringValidationNelError[T] = ValidationNel[ErrorCreadential, T]

  def username2EEE(in: String): Validation[String, UserName] =
    if (in.isEmpty) UserNameContainsEspaces("aa").toString.failure
    else if (in.contains(" ")) UserNameEmpty("aa").toString.failure
    else UserName(in).success

  def realname2EEE(in: String): Validation[String, FullName] =
    if (in.isEmpty) RealNameEmpty("aa").toString.failure
    else FullName(in).success
  val resStringEEE = (username2EEE("david pedro") |@| realname2EEE("")) (Credential.apply)
  println(resStringEEE)







  val isNonEmpty: String => ValidationNel[String, String] =
    str => Validation.liftNel(str)(_.isEmpty, "String is empty")
  val max10Characters: String => ValidationNel[String, String] =
    str => Validation.liftNel(str)(_.length > 10, "More than 10 characters")
  val startsWithUpper: String => ValidationNel[String, String] =
    str => Validation.liftNel(str)(_.headOption.exists(!_.isUpper), "Doesn't start with capital")

  val validateEverything: String => ValidationNel[String, String] =
    isNonEmpty |+| max10Characters |+| startsWithUpper

  println(validateEverything(""))
  println(validateEverything("aaassslalallala"))
  println(validateEverything("David"))

  import scalaz.syntax.applicative._

  println("aplicative")
  val a = ""
  val aa = "aaassslalallala"
  val resultAplicative = isNonEmpty(a) *> max10Characters(a) *> startsWithUpper(a)
  val resultAplicativeA = isNonEmpty(aa) *> max10Characters(aa) *> startsWithUpper(aa)
  println(resultAplicative)
  println(resultAplicativeA)
}