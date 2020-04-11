package es.david.cap041

object Data412 extends App {

  /*
   * Los ADTs pueden contener funciones puras
   */
  final case class UserConfiguration(accepts: Int => Boolean)
  /*
   * Pero los ADT contienen funciones que no se traducen perfectamente a la JVM, para el uso de codigo legacy
   */

}
