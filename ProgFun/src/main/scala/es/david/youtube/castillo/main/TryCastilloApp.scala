package es.david.youtube.castillo.main

import es.david.youtube.castillo.model.{AncientRecord, Citizen}

import scala.util.Try

// https://www.slideshare.net/MarkCanlas10/functional-error-handling-with-cats
object TryCastilloApp extends App {
  def parseAge(s: String): Try[Int] = Try{ s.toInt}

  def parseName(s: String): Try[String] = Try{s(3).toString}

  def parseRate(s: String): Try[Double] = Try{ s.toDouble}

  def parseRecord(record: AncientRecord): Try[Citizen] =
    for {
      age <- parseAge(record.age)
      name <- parseName(record.name)
      rate <- parseRate(record.rateOfIncome)
    } yield Citizen(age, name, rate)

  println(s"El valor es ${parseRecord(AncientRecord("3", "", "2"))}")
}
