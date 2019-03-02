package es.david.ADT


/*
un vagon se define por un entero y un string
Wagon = String * Int

un tren es una locomotora o un vago mas un tren
Train = Locomotive + Wagon * Train
que lo hemos definido por recurson
 */
sealed trait Train

case object Locomotive extends Train
case class Wagon(model: String, passengers: Int)
case class Nexus(wagon: Wagon, next: Train)

object Train {
  val cabeza: Locomotive.type = Locomotive
  val tren1: Wagon = Wagon("renfe", 80)
  val tren2: Wagon = Wagon("caf", 90)

  val trenP1: Nexus = Nexus(tren1, Locomotive)

}