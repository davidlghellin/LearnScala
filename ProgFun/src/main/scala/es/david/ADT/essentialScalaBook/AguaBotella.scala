package es.david.ADT.essentialScalaBook
/*
Bo led water has a size (an Int ), a source (which is a well, spring, or tap), and
a Boolean carbonated.
 */
sealed trait Fuente
final case object Pozo extends Fuente
final case object Manantial extends Fuente
final case object Grifo extends Fuente
final case class AguaEmbotellada(size: Int, source: Fuente, carbonatado: Boolean) extends Fuente
