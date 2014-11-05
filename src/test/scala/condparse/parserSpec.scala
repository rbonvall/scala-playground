package condparse

import org.scalatest.FunSpec
import CondParser._

class parserSpec extends FunSpec {

  describe("CondParser.number") {
    it("should parse numbers") {
      assert(parseAll(number, "-98765").get === -98765)
      assert(parseAll(number, "12.345").get === 12.345)
      assert(parseAll(number, "+12.345").get === 12.345)
      assert(parseAll(number, "-12.345").get === -12.345)
    }
    it("should not accept things with letters") {
      assert(parseAll(number, "-abc").isEmpty)
      assert(parseAll(number, "-abc123").isEmpty)
      assert(parseAll(number, "123abc").isEmpty)
    }
  }

  describe("CondParser.name") {
    it("should parse names") {
      assert(parse(name, "ABC").get === "ABC")
      assert(parse(name, "ABc").get === "ABc")
      assert(parse(name, "x").get === "x")
    }
  }

  describe("CondParser.nameList") {
    it("should parse list of names") {
      assert(parse(nameList, "ABC").get === Seq("ABC"))
      assert(parse(nameList, "AB,CD,EF").get === Seq("AB", "CD", "EF"))
      assert(parse(nameList, "AB, CD, EF").get === Seq("AB", "CD", "EF"))
    }
  }
}
