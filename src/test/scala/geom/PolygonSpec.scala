package geom

import org.scalatest.FunSpec

class PolygonSpec extends FunSpec {

  describe("Polygon") {
    val a = Point(0, 0)
    val b = Point(12, 5)
    val c = Point(9, 9)
    val d = Point(3, 9)
    val e = Point(0, 5)

    val p = Polygon(Seq(a, b, c, d, e))

    it("knows its segments") {
      val ss = Seq(
        Segment(a, b),
        Segment(b, c),
        Segment(c, d),
        Segment(d, e),
        Segment(e, a)
      )
      assert(p.segments === ss)
    }

    it("knows its perimeter") {
      assert(p.perimeter === 34)
    }

  }

}

