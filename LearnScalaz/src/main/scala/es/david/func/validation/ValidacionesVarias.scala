package es.david.func.validation

import scalaz.Scalaz._
import scalaz.{IList, Validation, ValidationNel}

object ValidacionesVarias extends App {

  case class Nombre(value: String) extends AnyVal

  case class Email(value: String) extends AnyVal

  case class Edad(value: Int) extends AnyVal

  case class Data(nombre: Nombre, email: Email, edad: Edad)


  def validarNombre(nombre: Nombre): Either[String, Nombre] = nombre match {
    case x if x.value.contains("www") => Left("incorrecto nombre")
    case x => Right(x)
  }

  def validarEmail(email: Email): Either[String, Email] = email match {
    case x if x.value.contains("@") => Right(x)
    case _ => Left("incorrecto mail")
  }

  def validarEdad(edad: Edad): Either[String, Edad] = edad match {
    case x if x.value < 0 => Left("incorrecta edad")
    case x => Right(x)
  }


  def toInts(maybeInts: List[String]): ValidationNel[Throwable, List[Int]] = {
    val validationList = maybeInts map { s =>
      Validation.fromTryCatchNonFatal(s.toInt :: Nil).toValidationNel
    }
    validationList reduce (_ +++ _)
  }

  println(toInts(List("1", "2", "3")))
  println(toInts(List("1", "2", "3", "x", "z")))

  //https://stackoverflow.com/questions/21351391/how-to-accumulate-errors-in-either
  def parseInt(s: String) = try Right(s.toInt) catch {
    case _: Throwable => Left("Not an integer!")
  }

  def checkNonzero(i: Int) = if (i == 0) Left("Zero!") else Right(i)

  def inverse(s: String): Either[String, Double] = for {
    i <- parseInt(s).right
    v <- checkNonzero(i).right
  } yield 1.0 / v

  def testData(data: Data): Either[IList[String], Data] = (
    validarNombre(data.nombre).validation.toValidationNel |@|
      validarEmail(data.email).validation.toValidationNel |@|
      validarEdad(data.edad).validation.toValidationNel
    ) (Data).leftMap(_.list).toEither


  def testData2(data: Data): ValidationNel[String, Data] = (
    validarNombre(data.nombre).validation.toValidationNel |@|
      validarEmail(data.email).validation.toValidationNel |@|
      validarEdad(data.edad).validation.toValidationNel
    ) (Data)

  val maloTodo = Data(Nombre("www"), Email(""), Edad(-9))
  val comproMaloTodo: Either[IList[String], Data] = testData(maloTodo)
  println(comproMaloTodo)

  val comproMaloTodo2 = testData2(maloTodo)
  println(comproMaloTodo2) // NomEmty
  println(comproMaloTodo2.leftMap(_.toList)) // List

  val bienTodo = Data(Nombre("David"), Email("david@com"), Edad(33))
  println(testData(bienTodo))


  case class ErrorCode(value: String) extends AnyVal

  case class Err(err: ErrorCode, msg: String)

  type MyValidation[T] = ValidationNel[Err, T]
}
