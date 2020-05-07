package es.david.youtube.castillo.adt

sealed trait HttpResponse

case class HttpSucces(s: String) extends HttpResponse
case class HttpClientError(s: String) extends HttpResponse