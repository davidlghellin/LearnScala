package es.david.ADT.essentialScalaBook

sealed trait Calculation
final case class Failure(mensaje: String) extends Calculation
final case class Success(num: Int) extends Calculation
