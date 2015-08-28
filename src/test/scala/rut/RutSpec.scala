package rut

import org.scalatest.FunSpec

class rutSpec extends FunSpec {
  describe("Rut") {

    it("extracts digits from numbers") {
      assert(Rut.getDigits(0)     === List())
      assert(Rut.getDigits(12345) === List(1, 2, 3, 4, 5))
    }

    it("computes the correct check digit") {
      assert(Rut.checkDigit(12345678) === '5')
      assert(Rut.checkDigit(22333444) === 'k')
    }

    it("validates ruts") {
      assert( Rut.isValid("12345678-5"))
      assert( Rut.isValid("22333444-k"))
      assert( Rut.isValid("22333444-K"))
      assert(!Rut.isValid("12345678-9"))
      assert(!Rut.isValid("12345678-k"))
      assert(!Rut.isValid("123456785"))
      assert(!Rut.isValid("12.345.678-5"))
    }

  }
}
