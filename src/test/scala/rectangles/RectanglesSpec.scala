package rectangles

import org.scalatest.FunSpec

trait Fixture {
  val r = Rectangle(5.0, 6.0, 7.0, 8.0)
}

class rectanglesSpec extends FunSpec {
   describe("Rectangle") {

     it("has the correct bottom-left corner") {
       new Fixture {
         assert(r.p0 === (5.0, 6.0))
       }
     }

     it("has the correct top-right corner") {
       new Fixture {
         assert(r.p1 === (12.0, 14.0))
       }
     }

     it("moves horizontally") {
       new Fixture {
         assert(r.hMove( 0.0) === r)
         assert(r.hMove( 1.5) === Rectangle(6.5, 6.0, 7.0, 8.0))
         assert(r.hMove(-0.3) === Rectangle(4.7, 6.0, 7.0, 8.0))
       }
     }

     it("moves vertically") {
       new Fixture {
         assert(r.vMove( 0.0) === r)
         assert(r.vMove( 1.5) === Rectangle(5.0, 7.5, 7.0, 8.0))
         assert(r.vMove(-0.3) === Rectangle(5.0, 5.7, 7.0, 8.0))
       }
     }

     it("trims") {
       new Fixture {
         assert(r.trimLeft  (0.5) === Rectangle(5.5, 6.0, 6.5, 8.0))
         assert(r.trimRight (0.5) === Rectangle(5.0, 6.0, 6.5, 8.0))
         assert(r.trimTop   (0.5) === Rectangle(5.0, 6.0, 7.0, 7.5))
         assert(r.trimBottom(0.5) === Rectangle(5.0, 6.5, 7.0, 7.5))
       }
     }

   }

}
