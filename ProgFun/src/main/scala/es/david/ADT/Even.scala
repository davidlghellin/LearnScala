package es.david.ADT

sealed abstract class Even(val value: Int)
case object Zero extends Even(0)
case class Next(previousEven: Even) extends Even(2 + previousEven.value)

