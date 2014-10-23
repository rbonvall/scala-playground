package stuff

import org.scalatest.FunSpec
import stuff.InsertIntoOrderedList._

class InsertIntoOrderedListSpec extends FunSpec {

  // val functions = Seq[Product2[(List[Int], Int) => List[Int], String]](
  //   ( insert1 _ , "insert1"),
  //   ( insert2 _ , "insert2")
  // )

  val functions = Seq[(List[Int], Int) => List[Int]](
    insert1 _,
    insert2 _
  )

  functions foreach { (insertFunction) =>
    describe("insert1") {
      it("should insert into correct position of a long list") {
        val l = List(11, 33, 55, 77, 88)
        assert(insertFunction(l,  0) == List( 0, 11, 33, 55, 77, 88))
        assert(insertFunction(l, 44) == List(11, 33, 44, 55, 77, 88))
        assert(insertFunction(l, 99) == List(11, 33, 55, 77, 88, 99))
        assert(1 + 1 === 2)
      }

      it("should insert into an empty list") {
        val l = List()
        assert(insertFunction(l, 22) == List(22))
        assert(1 + 1 === 2)
      }
    }
  }
}
