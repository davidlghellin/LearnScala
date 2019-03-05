package es.david.listas

import org.scalatest.{FlatSpec, Matchers}

class ConstanteValorListaTest extends FlatSpec with Matchers {

  behavior of "ConstanteValorListaTest"
  val lista: List[Int] = List(1, 2, 3, 4, 54, 6, 6, 6, 2, 124, 124, 1234, 124)

  it should "getConstante to list" in {
    val resultado = ConstanteValorLista.getConstante(lista, 0)

    resultado shouldBe a[List[_]]
  }

  it should "getConstante only result 0" in {
    val resultado = ConstanteValorLista.getConstante(lista, 0)

    resultado should contain only (0)
  }

}
