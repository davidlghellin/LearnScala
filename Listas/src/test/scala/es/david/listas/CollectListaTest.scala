package es.david.listas

import es.david.entidades.Donnut
import org.scalatest.{FlatSpec, Matchers}

class CollectListaTest extends FlatSpec with Matchers {
  "Collect" should "Precio" in {
    val donut1: Donnut = Donnut("Plain Donut", 1.50, 10)
    val donut2: Donnut = Donnut("Strawberry Donut", 2.0, 10)
    val listaDonnuts: List[Donnut] = List(donut1, donut2)

    val listaPrecios: List[Double] = CollectListas.getPreciosDonnut(listaDonnuts)

    listaPrecios shouldBe List(1.5, 2.0)
  }

  it should "nombres correctos" in {
    val donutNamesandPrices: List[Any] = List("Plain Donut", 1.5, "Strawberry Donut", 2.0, "Glazed Donut", 2.5)
    val nombresDonnut: List[String] = CollectListas.getString(donutNamesandPrices)

    nombresDonnut shouldBe List("Plain Donut", "Strawberry Donut", "Glazed Donut")
  }
}
