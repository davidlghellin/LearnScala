package es.david.listas

import org.scalatest.{FlatSpec, Matchers}

class ContainsSliceListasTest extends FlatSpec with Matchers {

  behavior of "ContainsSliceListasTest"
  val lista: List[Int] = List(1, 2, 3, 4, 5, 6)

  it should "getContains" in {
    val contieneEl1: Boolean = ContainsSliceListas.getContains(lista, 1)

    contieneEl1 shouldBe true
  }

  it should "getContainsSlice" in {
    val dosTres: Boolean = ContainsSliceListas.getContainsSlice(lista, List(2, 3))

    dosTres shouldBe true
  }

  it should "false getContainsSlice" in {
    val dosTres: Boolean = ContainsSliceListas.getContainsSlice(lista, List(2, 5))

    dosTres shouldBe false
  }

}
