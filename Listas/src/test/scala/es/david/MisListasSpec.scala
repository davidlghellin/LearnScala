package es.david

import org.scalatest.FunSpec

class MisListasSpec extends FunSpec {
  describe("getLista10") {
    it("returns leng 10") {
      assert(MisListas.getLista10().length === 10)
    }
  }

}
