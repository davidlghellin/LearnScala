package es.david.youtube.castillo.main

import cats._
import es.david.youtube.castillo.adt.ParsingErr
//import cats.effect._
import cats.implicits._

object MonadErrorCastilloApp extends App {
  type NukeMonadError[M[_]] = MonadError[M, ParsingErr]

  def parseAge[M[_] : NukeMonadError](s: String): M[Age] =
    Age().pure[M]

  def parseName[M[_] : NukeMonadError](s: String): M[Name] =
    Name().pure[M]

  def parseRate[M[_] : NukeMonadError](s: String): M[Rate] =
    Rate().pure[M]
}

case class Age()
case class Name()
case class Rate()