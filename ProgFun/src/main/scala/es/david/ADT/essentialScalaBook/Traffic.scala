package es.david.ADT.essentialScalaBook

/*
A traffic light is red, green, or yellow
 */
sealed trait Traffic
final case object Rojo  extends Traffic
final case object Verde extends Traffic
final case object Azul  extends Traffic