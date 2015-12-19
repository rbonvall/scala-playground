package geom

import org.scalatest.FunSpec

class PointSpec extends FunSpec {
  import Point.π

  describe("Point") {

    it("supports addition, substraction and multiplication by scalar") {
      assert(Point(20, 30) + Point(3, 7) === Point(23, 37))
      assert(Point(20, 30) - Point(3, 7) === Point(17, 23))
      assert(Point(-7, 3) * -4 === Point(28, -12))
    }

    it("knows its polar angle") {
      assert(Point( 1,  0).θ === 0)
      assert(Point( 1,  1).θ ===     π/4)
      assert(Point( 0,  1).θ ===     π/2)
      assert(Point(-1,  0).θ ===     π  )
      assert(Point( 0, -1).θ === 3 * π/2)
    }

    it("knows its distance to another point") {
      assert((Point(2, -2) distanceTo Point(-3, 10)) === 13.0)
    }

  }

  describe("PointTupleConversions") {

    it("provides an implicit conversion from tuples") {
      import PointTupleConversions.tupleToPoint
      assert(((1.0, 1.0) distanceTo (4.0, 5.0)) === 5.0)
      assert((-1.0, -1.0).θ === 5 * π/4)
    }

    it("provides an implicit conversion to tuples") {
      import PointTupleConversions.pointToTuple
      assert(Point(9, 8)._1 === 9)
      assert(List(Point(1, 2), Point(3, 4)).unzip === (List(1, 3), List(2, 4)))
    }

  }

}

