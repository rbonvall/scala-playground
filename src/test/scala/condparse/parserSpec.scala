package condparse

import org.scalatest.FunSpec
import CondParser._

class parserSpec extends FunSpec {

  describe("number parser being fed directly") {
    import scala.util.parsing.input.CharSequenceReader
    it("parses a number") {
      val result = number(new CharSequenceReader("-3.14159abc"))
      assert(result match {
        case Success(n, rest) => n === -3.14159 &&
            rest.source.subSequence(8, 11).toString === "abc"
        case failure          => false
      })
    }
    it("doesn't parse a word") {
      val result = number(new CharSequenceReader("stuff"))
      assert(result match {
        case Success(_, _) => false
        case failure       => true
      })
    }
    // A less cumbersome way of feeding the parsers
    // is by using the helper functions
    // parse(parser, input) and parseAll(parser, input).
  }

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
    it("should not accept an empty list") {
      parsingFails(nameList, "")
    }
  }

  describe("CondParser.negatedNameList") {
    it("should parse negated list of names") {
      parsingSucceeds(negatedNameList, "NOT(ABC)", Seq("ABC"))
      parsingSucceeds(negatedNameList, "NOT(AB,CD,EF)", Seq("AB", "CD", "EF"))
      parsingSucceeds(negatedNameList, "NOT(AB, CD, EF)", Seq("AB", "CD", "EF"))
      parsingSucceeds(negatedNameList, "NOT(  AB , CD , EF )", Seq("AB", "CD", "EF"))
    }
    it("should not accept an empty negated list") {
      parsingFails(negatedNameList, "NOT()")
    }

  }
}
