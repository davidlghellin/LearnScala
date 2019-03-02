package es.david.ADT.essentialScalaBook

sealed trait Comida
case object Antilope extends Comida
case object ComidaTigre extends Comida
case object Regaliz extends Comida
final case class GatoComida(food: String) extends Comida

sealed trait Felino2 {
  def cena: Comida
}

final case class Leon2() extends Felino2 {
  def cena: Comida = Antilope
}

final case class Tigre2() extends Felino2 {
  def cena: Comida = ComidaTigre
}

final case class Pantera2() extends Felino2 {
  def cena: Comida = Regaliz
}

final case class Gato2(food:String) extends Felino2 {
  def cena: Comida = GatoComida(food)
}