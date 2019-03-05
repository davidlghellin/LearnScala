package es.david.listas

import org.scalatest.{FlatSpec, Matchers}

class CurryListasTest extends FlatSpec with Matchers {
  val nums = List(1, 2, 3, 4, 5, 6, 7, 8)

  behavior of "CurryListasTest"

  it should "filter elementos mayor que 0" in {
    val filtradosPares: List[Int] = CurryListas.filter(nums, CurryListas.mod2)

    all(filtradosPares) should be > 0
  }

  it should "filter elementos pares" in {
    val filtradosPares: List[Int] = CurryListas.filter(nums, CurryListas.mod2)

    filtradosPares shouldBe List(2, 4, 6, 8)
  }

  "Patter" should "filter elementos mayor que 0" in {
    val filtradosPares: List[Int] = CurryListas.filterPatter(nums, CurryListas.mod2)

    all(filtradosPares) should be > 0
  }

  it should "filter elementos pares" in {
    val filtradosPares: List[Int] = CurryListas.filterPatter(nums, CurryListas.mod2)

    filtradosPares shouldBe List(2, 4, 6, 8)
  }

  "curryBinaryOperator" should "suma currificada" in {
    val suma1 = CurryListas.addCurried(1)

    suma1(1) shouldBe 2
  }

}
