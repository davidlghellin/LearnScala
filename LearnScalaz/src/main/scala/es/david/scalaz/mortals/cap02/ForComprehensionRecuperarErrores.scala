package es.david.scalaz.mortals.cap02

object ForComprehensionRecuperarErrores extends App {
  // Cuando llamamos a un metodo que devuelve Option, a veces queremos que se aplicque otro metodo y así sucesivamentge
  // como cuando usamos un cache
  def getFromRedis(s: String): Option[String] = None

  def getFromSql(s: String): Option[String] = Option("2ª vía")

  val key: String = "hola"
  println(getFromRedis(key) orElse getFromSql(key))

}
