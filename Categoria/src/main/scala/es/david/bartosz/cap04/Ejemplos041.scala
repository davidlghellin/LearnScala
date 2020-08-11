package es.david.bartosz.cap04

object Ejemplos041 extends App {
  val esPar: Int => Boolean = (x: Int) => x % 2 == 0
  val esImpar: Int => Boolean = (x: Int) => !esPar(x)

  // INI Ejemplo
  val esParLog: Int => (Boolean, String) = {
    (x: Int) => (x % 2 == 0, "esParLog")
  }
  val esImparLog: Int => (Boolean, String) = {
    (x: Int) => {
      val p1: (Boolean, String) = esParLog(x)
      val p2 = negate(p1._1)
      (p2._1, p1._2 + p2._2)
    }
  }

  val negate: Boolean => (Boolean, String) = {
    (x: Boolean) => (!x, "not")
  }
  // FIN Ejemplo
}
