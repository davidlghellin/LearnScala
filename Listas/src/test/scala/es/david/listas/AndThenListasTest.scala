package es.david.listas

import org.scalatest.{FlatSpec, Matchers}
import es.david.listas._

class AndThenListasTest extends FlatSpec with Matchers {

  "AndThen" should "h" in {
    assert(AndThenListas.h("hola") === 8)
  }

  "Auxiliar" should "listas" in {
    val lista: List[String] = List("Esto son", "10")
    val listaNum: List[Int] = AndThenListas.calculaLeng(lista)
    assert(listaNum === List(8, 2))
  }
  it should "sumar" in {
    assert(AndThenListas.sumar(List(8, 2)) === 10)
  }
}