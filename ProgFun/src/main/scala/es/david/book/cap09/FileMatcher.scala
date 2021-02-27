package es.david.book.cap09

object FileMatcher extends App {

  private def ficherosAqui = new java.io.File(".").listFiles

  // Queremos hacer un filtro por ficheros que acaben en lo que pasemos por parametro
  def filesFin(query: String) =
    for (
      file <- ficherosAqui
      if file.getName.endsWith(query)
    ) yield file

  // Luego nos piden que contenga
  def filesContains(query: String) =
    for (
      file <- ficherosAqui
      if file.getName.contains(query)
    ) yield file

  // Luego por expresion regular
  def filesRegex(query: String) =
    for (
      file <- ficherosAqui
      if file.getName.matches(query)
    ) yield file

  // Como podemos ver estamos repitiendo codigo
  // Veamos una nueva signatura de la funcion
  def filesMatching1(query: String, matcher: (String, String) => Boolean) = {
    for (
      file <- ficherosAqui
      if matcher(file.getName, query)
    ) yield file
  }

  def filesEndingF(query: String) =
    filesMatching1(query, _.endsWith(_))

  def filesContainsF(query: String) =
    filesMatching1(query, _.contains(_))

  def filesRegexgF(query: String) =
    filesMatching1(query, _.matches(_))

  // Usando Closures
  def filesMatching2(matcher: String => Boolean) =
    for (
      file <- ficherosAqui
      if matcher(file.getName)
    ) yield file

  def filesEnding2(query: String) =
    filesMatching2(_.endsWith(query))

  def filesContaining2(query: String) =
    filesMatching2(_.contains(query))

  def filesRegex2(query: String) =
    filesMatching2(_.matches(query))


  println("fin sh")
  filesEndingF("sh").foreach(println)
  println("*" * 5)
  println("contains tar")
  filesContainsF("tar").foreach(println)
}
