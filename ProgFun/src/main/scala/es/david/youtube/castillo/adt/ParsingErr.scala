package es.david.youtube.castillo.adt

sealed trait ParsingErr

case class UnreadableAge(s: String) extends ParsingErr
case class UnreadableName(s: String) extends ParsingErr
case class UnreadableRate(s: String) extends ParsingErr
