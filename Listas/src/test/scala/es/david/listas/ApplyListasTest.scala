package es.david.listas

import org.scalatest.{FlatSpec, Matchers}
import es.david.listas._

class ApplyListasTest  extends FlatSpec with Matchers {
    "Lista" should "elemento 1" in {
        val lista: List[Int] = List(9,8,7,6)

        ApplyListas.getApply(lista, 1) shouldBe 8
        ApplyListas.getApply(lista, 1) shouldEqual 8
        
        ApplyListas.getApply(lista, 1) should not be 83
        ApplyListas.getApply(lista, 1) should not equal 83
    }
}