package es.david.cap061

import scalaz.Scalaz._
import scalaz.ValidationNel

object ValidationNel extends App {

  final case class UserName(value: String) extends AnyVal

  final case class FullName(value: String) extends AnyVal

  final case class Credential(user: UserName, name: FullName)

  def username(in: String): ValidationNel[String, UserName] =
    if (in.isEmpty) "emty usernema".failureNel
    else if (in.contains(" ")) "username contains espaces".failureNel
    else UserName(in).success

  def realname(in: String): ValidationNel[String, FullName] =
    if (in.isEmpty) "empty real name".failureNel
    else FullName(in).success

  val resTodosErrores = (username("david pedro") |@| realname("")) (Credential.apply)
  println(resTodosErrores)
  println((username("David") |@| realname("Lopez")) (Credential.apply))


  ////////
  val yes: ValidationNel[String, Double] = 3.14.successNel[String]
  val doh: ValidationNel[String, Double] = "Error".failureNel[Double]

  def addTwo(x: Double, y: Double) = x + y

  println((yes |@| yes) (addTwo))
  println((doh |@| doh) (addTwo))

  println((yes |@| yes) (_ + _))
  println((doh |@| doh) (_ + _))


  println((doh findSuccess doh findSuccess yes) )


}
