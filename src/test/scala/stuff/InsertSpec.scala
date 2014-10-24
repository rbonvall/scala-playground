package stuff

import org.scalatest.FunSpec
import stuff.InsertIntoOrderedList._

class InsertIntoOrderedListSpec extends FunSpec {

  def functions[T <% Ordered[T]] = Seq[(List[T], T) => List[T]](
    insert0 _,
    insert1 _,
    insert2 _,
    insert3 _
  )

  functions[Int].zipWithIndex foreach { case (insertFunction, index) =>
    describe(s"insert$index") {
      it("should insert into correct position of a long list") {
        val l = List(11, 33, 55, 77, 88)
        assert(insertFunction(l,  0) === List( 0, 11, 33, 55, 77, 88))
        assert(insertFunction(l, 44) === List(11, 33, 44, 55, 77, 88))
        assert(insertFunction(l, 99) === List(11, 33, 55, 77, 88, 99))
      }

      it("should insert into an empty list") {
        val l = List()
        assert(insertFunction(l, 22) === List(22))
      }
    }
  }
}
