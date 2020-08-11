package es.david.cap054

import scalaz.IList
import scalaz.Scalaz._

object TraverseApp extends App {
  val freedom: List[String] =
    """We campaign for these freedoms because everyone deserves them.
     With these freedoms, the users (both individually and collectively)
     control the program and what it does for them."""
      .split("\\s+")
      .toList

  def clean(s: String): String = s.toLowerCase.replaceAll("[,.()]+", "")

  /*
    digamos que tenemos una lista de palabras y que deseamos borrar las palabras que ya
    hemos encontrado. El algoritmo de filtrado no permite procesar las palabras de la
    lista una segunda vez de modo que pueda escalarse a un stream infinito
  */
  val res: String = freedom
    .mapAccumL(Set.empty[String]) { (seen, word) =>
      val cleaned = clean(word)
      (seen + cleaned, if (seen(cleaned)) "_" else word)
    }
    ._2
    .intercalate(" ")
  println(res)
}

