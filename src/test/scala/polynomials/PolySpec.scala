package polynomials

import org.scalatest.FunSpec

class PolySpec extends FunSpec {

  val zero      = Poly()
  val three     = Poly( 3)
  val identity  = Poly( 0,  1)
  val linear    = Poly( 5, -2)
  val quadratic = Poly( 3, -1, 2)
  val cubic     = Poly(-2, -4, 0, 1)
  val square    = Poly( 0,  0, 1, 0, 0, 0, 0)
  val long      = Poly( 2,  2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
  val ones      = Poly( 1,  1, 1)
  val all = List(zero, three, identity, linear, quadratic, cubic, square, long, ones)

  describe("Poly") {

    it("has a pretty representation") {
      assert(zero.pretty === "0")
      assert(quadratic.pretty === "3-x+2x²")
      assert(cubic.pretty === "-2-4x+x³")
      assert((-cubic).pretty === "2+4x-x³")
      assert(ones.pretty === "1+x+x²")
      assert((-ones).pretty === "-1-x-x²")
    }

    it("knows its degree") {
      assert(zero.degree === -1) // https://en.wikipedia.org/wiki/Degree_of_a_polynomial#Degree_of_the_zero_polynomial
      assert(three.degree === 0)
      assert(linear.degree === 1)
      assert(square.degree === 2)
      assert(long.degree === 12)
    }

    it("can be evaluated") {
      assert(zero(3.14) === 0)
      assert(three(3.14) === 3)
      assert(identity(3.14) === 3.14)
      assert((-10 to 10).toList.map(linear(_)) === (25 to -15 by -2).toList)
      assert(square(+3) === 9)
      assert(square(-3) === 9)
    }

    it("can be added to another polynomial of any degree") {
      assert(quadratic + cubic === Poly(1, -5, 2, 1))
    }

    it("can be added to another polynomial, even if it results in a lesser-degree one") {
      assert(Poly(2, 3, 4) + Poly(5, 1, -4) === Poly(7, 4))
    }

    it("can be multiplied by a scalar") {
      assert(Poly(2, -3, 4) * 3 === Poly(6, -9, 12))
      assert(Poly(2, -3, 4) * 0 === Poly())
    }

  }

}
