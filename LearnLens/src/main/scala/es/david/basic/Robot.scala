package es.david.basic

abstract class Robot(position: Double) {
  def moveBy(d: Double): Robot
}

object RobotApp extends App {
  //  val nono: Robot = ???
  //  nono.moveBy(100.5) // metros
  //  nono.moveBy(3) // km
  //  nono.moveBy(2.5) // yardas

  // Metro to yardas
  val metroToYard: Iso[Metros, Yardas] = Iso(
    (m: Metros) => Yardas(m.value * 1.09361)
    , (m: Yardas) => Metros(m.value / 1.09361)
  )

  val metroToKm: Iso[Metros, KilomMetros] = Iso(
    (m: Metros) => KilomMetros(m.value / 1000)
    , (m: KilomMetros) => Metros(m.value * 1000)
  )

  val yardaToMilla: Iso[Yardas, Milla] = Iso(
    (m: Yardas) => Milla(m.value / 1000)
    , (m: Milla) => Yardas(m.value * 1000)
  )

//  val a = metroToYard andThen yardaToMilla
}

// Definimos las medidas
case class Metros(value: Double)

case class Yardas(value: Double)

case class KilomMetros(value: Double)

case class Milla(value: Double)

class RobotMetros(position: Double) {
  def moveBy(d: Metros): RobotMetros = ???
}

// pero no compilaria si queremos el de yardas
// podriamos sobrecargar el robot