package week6

import org.scalatest.FunSpec
import polynomials.Poly

class PolySpec extends FunSpec {

  describe("Poly") {
    it("should add two polynomials") {
      val p1          = new Poly(         1 → 2.0, 3 →  4.0, 5 → 6.2)
      val p2          = new Poly(0 → 3.0,          3 →  7.0)
      val expectedSum = new Poly(0 → 3.0, 1 → 2.0, 3 → 11.0, 5 → 6.2)
      val actualSum = p1 + p2
      assert(expectedSum.terms == actualSum.terms)
    }

  }
}

