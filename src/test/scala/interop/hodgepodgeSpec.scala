package interop

import org.scalatest.FunSpec
import interop._

class hodgepodgeSpec extends FunSpec {
  describe("Date.next") {
    it("works") {
      assert(Date(2014, 11, 11).next === Date(2014, 11, 12))
      assert(Date(2014, 11, 30).next === Date(2014, 12,  1))
      assert(Date(2014, 12, 30).next === Date(2014, 12, 31))
      assert(Date(2014, 12, 31).next === Date(2015,  1,  1))
    }
  }
  describe("Date.<") {
    it("works for different years") {
      assert(Date(2013, 11, 11) < Date(2014,  9, 15))
      assert(Date(2013, 11, 11) < Date(2014, 11,  9))
      assert(Date(2013, 11, 11) < Date(2014, 11, 11))
      assert(Date(2013, 11, 11) < Date(2014, 11, 30))
      assert(Date(2013, 11, 11) < Date(2014, 12, 15))
    }
    it("works for different months within a year") {
      assert(Date(1999,  1, 30) < Date(1999,  9, 15))
      assert(Date(1999,  1, 15) < Date(1999,  9, 30))
      assert(Date(1999,  2, 28) < Date(1999,  3,  1))
    }
    it("works for different days within a calendar month") {
      assert(Date(1983,  5, 14) < Date(1999,  5, 15))
      assert(Date(1983,  5,  1) < Date(1999,  5, 30))
    }
  }
  describe("Date.-") {
    it("works") {
      assert(Date(1983,  5, 14) - Date(1983,  5, 14) ===   0)
      assert(Date(1983,  5, 24) - Date(1983,  5, 14) ===  10)
      assert(Date(1983,  5, 14) - Date(1983,  5, 24) === -10)
    }
  }
}

