package stuff

import org.scalatest.FunSpec

class FunProgInScalaSpec extends FunSpec {
  import FunProgInScala._

  describe("isSorted") {
    it("works") {
      assert( isSorted[Int](Array(2, 4, 6, 8), _ > _))
      assert(!isSorted[Int](Array(2, 4, 3, 8), _ > _))
      assert( isSorted[Int](Array(1, 1, 2, 2), _ >= _))
      assert( isSorted[Int](Array(30, 11, 52, 3), _ % 10 > _ % 10))
      assert( isSorted[String]("xyz defg hijkl".split(" "), _.length > _.length))
      assert(!isSorted[String]("xyz defg hijkl".split(" "), _ > _))
    }
  }

  describe("partial1") {
    it("works") {
      def exclamate(x: String, n: Int) = x + "!" * n
      assert(exclamate("Hola", 3) === "Hola!!!")
      val shoutHi = partial1("Hi", exclamate)
      assert(shoutHi(5) === "Hi!!!!!")
    }
  }

  describe("curry") {
    it("works") {
      def mul(x: Int, y: Int) = x * y
      val triple = curry(mul)(3)
      assert(triple(5) === 15)
    }
  }

  describe("uncurry") {
    it("works") {
      def mul(x: Int)(y: Int) = x * y
      val m = uncurry(mul)
      assert(m(3, 5) === 15)
    }
  }

  describe("compose") {
    it("works") {
      def f(x: Int) = 3 * x
      def g(x: Int) = 1 + x
      val z = compose(f, g)
      assert(z(10) === 33)
    }
  }

}
