package es.david.youtube.castillo.main

import es.david.youtube.castillo.adt._
import es.david.youtube.castillo.model.{AncientRecord, Citizen}

// https://www.youtube.com/watch?v=KQZjOJjnHIE
// https://www.slideshare.net/MarkCanlas10/functional-error-handling-with-cats
object EitherCastilloApp extends App {
  def parseAge(s: String): Either[UnreadableAge, Int] = Right(2)

  def parseName(s: String): Either[UnreadableName, String] = s match {
    case x if x.length < 5 => Left(UnreadableName(""))
    case x => Right(x)
  }

  def parseRate(s: String): Either[UnreadableRate, Double] = Right(3d)

  def parseRecord(record: AncientRecord): Either[ParsingErr, Citizen] =
    for {
      age <- parseAge(record.age)
      name <- parseName(record.name)
      rate <- parseRate(record.rateOfIncome)
    } yield Citizen(age, name, rate)

  println(s"El valor es ${parseRecord(AncientRecord("", "", ""))}")

  def present(resu: Either[ParsingErr, Citizen]): HttpResponse =
    resu
      .fold(
        l => httpError(l)
        , r => httpSucess(r)
      )

  def presentLargo(resu: Either[ParsingErr, Citizen]): HttpResponse = resu match {
    case Left(x) => httpError(x)
    case Right(x) => httpSucess(x)
  }

  def httpError(err: ParsingErr): HttpClientError = HttpClientError(err.toString)

  def httpSucess(c: Citizen): HttpSucces = HttpSucces(c.toString)

  println(s"El valor es ${present(parseRecord(AncientRecord("", "", "")))}")


  val res: Either[ParsingErr, Citizen] = parseRecord(AncientRecord("3", "Davidl", "2"))
  val httpResponse: HttpResponse =
    res
      .fold(httpError, httpSucess)
  println(httpResponse)

  import cats.implicits._

  val httpResponse2: HttpResponse =
    res
      .map(httpSucess)
      .leftMap(httpError)
      .merge
  println(httpResponse2)
}
