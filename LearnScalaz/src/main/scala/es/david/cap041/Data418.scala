package es.david.cap041

object Data418 {

  // Prefiero el copdroducto al producto
  case class Complejidad8 (a: Boolean, b: Boolean, c: Boolean)

  // veamos ahora una complejidad 3
  sealed abstract class Config
  object Config {
    case object A extends Config
    case object B extends Config
    case object C extends Config
  }
}
