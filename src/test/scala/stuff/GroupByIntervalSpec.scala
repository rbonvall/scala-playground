package stuff

import org.scalatest.FunSpec

class GroupByIntervalSpec extends FunSpec {
  describe("GroupByInterval") {
    it("works when there are no contiguous intervals") {
      val input = List(1, 3, 5, 7)
      val expected = List((1, 1), (3, 3), (5, 5), (7, 7))
      assert(GroupByInterval(input) === expected)
    }
    it("works when there is only one contiguous interval") {
      val input = List(3, 4, 5, 6)
      val expected = List((3, 6))
      assert(GroupByInterval(input) === expected)
    }
    it("works on a more general list of values") {
      val input = List(1, 2, 3, 5, 7, 8, 11, 12, 13, 14, 17, 19, 20)
      val expected = List((1, 3), (5, 5), (7, 8), (11, 14), (17, 17), (19, 20))
      assert(GroupByInterval(input) === expected)
    }
    it("works on an empty list") {
      val input = List()
      val expected = List()
      assert(GroupByInterval(input) === expected)
    }
  }
}
