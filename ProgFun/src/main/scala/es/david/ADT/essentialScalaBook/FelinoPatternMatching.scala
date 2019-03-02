package es.david.ADT.essentialScalaBook

sealed trait FelinoPatternMatching {
  def dinner: Comida =
    this match {
      case LionPM() => Antilope
      case TigerPM() => ComidaTigre
      case PantherPM() => Regaliz
      case CatPM(favouriteFood) => GatoComida(favouriteFood)
    }
}

final case class LionPM() extends FelinoPatternMatching
final case class TigerPM() extends FelinoPatternMatching
final case class PantherPM() extends FelinoPatternMatching
final case class CatPM(favouriteFood: String) extends FelinoPatternMatching



object Cena {
  def dinner(felino: FelinoPatternMatching): Comida = {
    felino match {
      case LionPM() => Antilope
      case TigerPM() => ComidaTigre
      case PantherPM() => Regaliz
      case CatPM(comida) => GatoComida(comida)
    }
  }
}

object MainFelinoPattern extends App {
  val leon: FelinoPatternMatching = new LionPM
  val comida: Comida = Cena.dinner(leon)

  println(comida)
  println(leon.dinner)

  val gato = CatPM("sardinas")
  println(gato.dinner)
}