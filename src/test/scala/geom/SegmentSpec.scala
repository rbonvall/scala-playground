package geom

import org.scalatest.FunSpec

class SegmentSpec extends FunSpec {

  describe("Segment") {

    it("knows if a point belongs to it") {
      val seg = Segment(Point(1, 1), Point(4, 5))
      assert( seg.contains(seg.start))
      assert( seg.contains(seg.end))
      assert( seg.contains(seg.start *  0.4 + seg.end * 0.6))
      assert(!seg.contains(seg.start * -0.1 + seg.end * 1.1))
      assert(!seg.contains(Point(-1000, -2000)))
    }

  }

  describe("SegmentTupleConversions") {

    it("provides an implicit conversion from tuples") {
      import SegmentTupleConversions.tupleToSegment
      assert((Point(1.0, 1.0), Point(4.0, 5.0)).length === 5.0)
    }

    it("provides an implicit conversion to tuples") {
      import SegmentTupleConversions.segmentToTuple
      assert(Segment(Point(9, 8), Point(3, 4))._1 === Point(9, 8))
    }

  }

}

