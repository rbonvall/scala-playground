package hello

import org.scalatest.FunSpec

class HelloSpec extends FunSpec {

  describe("naturals") {

    it("should make one plus one equal to two") {
      assert(1 + 1 === 2)
    }

  }
}
