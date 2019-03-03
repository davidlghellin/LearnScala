package es.david

import org.scalatest.{FlatSpec, Matchers}
import es.david._

class MisListasTest extends FlatSpec with Matchers {

  "Length" should "Correcta" in {
    assert (MisListas.getLista10().length === 10)
  }
  

}