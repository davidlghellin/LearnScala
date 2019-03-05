package es.david.listas

import org.scalatest.{FlatSpec, Matchers}

class CombinationsListTest extends FlatSpec with Matchers {
  val lista: String = "abbbbbbc"

  "Combinations" should "tener tamanyo correcto" in {
    val result = CombinationsList.getCombinaciones(lista, 2)

    result should have size 4
  }

  it should "elementos correctos" in {
    val result = CombinationsList.getCombinaciones(lista, 2)

    result should contain("ab")
    result should contain("ac")
    result should contain("bb")
    result should contain("bc")
  }

  it should "orden correcto" in {
    val result = CombinationsList.getCombinaciones(lista, 2)

    result(0) should be("ab")
    result(1) should be("ac")
    result(2) shouldBe ("bb")
    result(3) shouldBe ("bc")
  }
}