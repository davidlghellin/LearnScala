package es.david.cap041

object Data413 extends App {

  /*
   * Es importante que nosotros usemos sealed abstract class y no abstract class, cuando definamos nuestros data type
   * Cuando hacemos el sealed significa que todos los subtipos deben ser definidos en el mismo fichero
   * permitiendo al compilador conocer los tipos para el patter maching exausitvo, macros, eliminar boilerplate ...
   */

  sealed abstract class Foo

  final case class Bar(flag: Boolean) extends Foo

  final case object Baz extends Foo

  def thing(foo: Foo) = foo match {
    case Bar(_) => true
    case Baz => false // sino ponemos esta linea o la de case _ petaría el compilador
  }
}
