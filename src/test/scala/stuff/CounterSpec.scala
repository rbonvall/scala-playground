package stuff

import org.scalatest.FunSpec
import stuff.Counter._

class CounterSpec extends FunSpec {

  describe("countMap") {

    it("should count elements of an unordered list") {
      val l = List(66, 77, 11, 77, 99, 77, 11)
      assert(countMap(l) === Map(
        11 -> 2,
        66 -> 1,
        77 -> 3,
        99 -> 1
      ))
    }

    it("should be able to count nothing") {
      assert(countMap(List[String]()) === Map())
    }

    it("should count characters from a string") {
      assert(countMap("casas".toList) === Map(
        'a' -> 2,
        'c' -> 1,
        's' -> 2
      ))
    }

  }
}
