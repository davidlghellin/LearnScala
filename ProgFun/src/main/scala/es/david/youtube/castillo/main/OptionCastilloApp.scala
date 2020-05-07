package es.david.youtube.castillo.main

import es.david.youtube.castillo.model.{AncientRecord, Citizen}

// https://www.slideshare.net/MarkCanlas10/functional-error-handling-with-cats
object OptionCastilloApp extends App {
  //  def parseRecord(rec: AncientRecord): F[Citizen]
  def parseAge(s: String): Option[Int] = Some(3)

  def parseName(s: String): Option[String] = None

  def parseRate(s: String): Option[Double] = Some(2d)

  def parseRecord(record: AncientRecord): Option[Citizen] =
    for {
      age <- parseAge(record.age)
      name <- parseName(record.name)
      rate <- parseRate(record.rateOfIncome)
    } yield Citizen(age, name, rate)

  println(s"El valor es ${parseRecord(AncientRecord("", "", ""))}")
}
