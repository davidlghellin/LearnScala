package es.david.ADT.essentialScalaBook

sealed trait Felino

final case class Leon() extends Felino
final case class Tigre() extends Felino
final case class Pantera() extends Felino
final case class Gato(comidaFav: String) extends Felino

// uso de polimorfismo
