package week6

import org.scalatest.FunSpec
import mnemo._

class MnemoSpec extends FunSpec {

  describe("charCode") {
    it("should map each character to its digit") {
      assert(charCode('N') === '6')
      assert(charCode('D') === '3')
      assert(charCode('Y') === '9')
      assert(charCode('Z') === '9')
    }
  }

  describe("wordCode") {
    it("should work") {
      assert(wordCode("HOLA") === "4652")
      assert(wordCode("Java") === "5282")
    }
  }
}


