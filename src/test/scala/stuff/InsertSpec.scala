package stuff

import org.scalatest.FunSpec
import stuff.InsertIntoOrderedList._

class InsertIntoOrderedListSpec extends FunSpec {

  describe("insert") {

    it("should insert into correct position of a long list") {
      val l = List(11, 33, 55, 77, 88)
      assert(insert1(l,  0) == List( 0, 11, 33, 55, 77, 88))
      assert(insert1(l, 44) == List(11, 33, 44, 55, 77, 88))
      assert(insert1(l, 99) == List(11, 33, 55, 77, 88, 99))
      assert(1 + 1 === 2)
    }

    it("should insert into an empty list") {
      val l = List()
      assert(insert1(l, 22) == List(22))
      assert(1 + 1 === 2)
    }

  }
}
