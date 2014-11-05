package condparse

import org.scalatest.FunSpec
import CondParser._

class parserSpec extends FunSpec {

  def parsingSucceeds[T](parser: Parser[T], input: String, expected: T) = {
    val output = parseAll(parser, input)
    assert(output.successful && output.get === expected)
  }

  def parsingFails[T](parser: Parser[T], input: String) = {
    val output = parseAll(parser, input)
    assert(output.isEmpty)
  }

  describe("CondParser.number") {
    it("should parse numbers") {
      parsingSucceeds(number, "-98765", -98765)
      parsingSucceeds(number, "12.345", 12.345)
      parsingSucceeds(number, "+12.345", 12.345)
      parsingSucceeds(number, "-12.345", -12.345)
    }
    it("should not accept things with letters") {
      parsingFails(number, "-abc")
      parsingFails(number, "-abc123")
      parsingFails(number, "123abc")
    }
  }

  describe("CondParser.name") {
    it("should parse names") {
      parsingSucceeds(name, "ABC", "ABC")
      parsingSucceeds(name, "ABc", "ABc")
      parsingSucceeds(name, "x", "x")
    }
  }

  describe("CondParser.nameList") {
    it("should parse list of names") {
      parsingSucceeds(nameList, "ABC", Seq("ABC"))
      parsingSucceeds(nameList, "AB,CD,EF", Seq("AB", "CD", "EF"))
      parsingSucceeds(nameList, "AB, CD, EF", Seq("AB", "CD", "EF"))
    }
  }

  describe("CondParser.negatedNameList") {
    it("should parse negated list of names") {
      parsingSucceeds(negatedNameList, "NOT(ABC)", Seq("ABC"))
      parsingSucceeds(negatedNameList, "NOT(AB,CD,EF)", Seq("AB", "CD", "EF"))
      parsingSucceeds(negatedNameList, "NOT(AB, CD, EF)", Seq("AB", "CD", "EF"))
      parsingSucceeds(negatedNameList, "NOT(  AB , CD , EF )", Seq("AB", "CD", "EF"))
    }
  }
}
