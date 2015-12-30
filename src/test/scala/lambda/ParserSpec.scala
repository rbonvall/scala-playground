package lambda

import org.scalatest.FunSpec

class ParserSpec extends FunSpec {

  def itParses(input: String)(expected: ⇒ Expression) = Parser(input) match {
    case Parser.Success(result, _) ⇒ assert(result == expected)
    case Parser.Failure(error, _)  ⇒ fail(error)
    case Parser.Error  (error, _)  ⇒ fail(error)
  }

  def itDoesntParse(input: String) = Parser(input) match{
    case Parser.Success(result, _) ⇒ fail(s"Shouldn't have parsed.")
    case Parser.Failure(error, _)  ⇒ assert(true)
    case Parser.Error  (error, _)  ⇒ fail(error)
  }

  describe("Parser") {

    it("parses good stuff") {
      itParses("x") {
        Variable("x")
      }
      itParses("(x)") {
        Variable("x")
      }
      itParses("λx.x") {
        Abstraction(Variable("x"), Variable("x"))
      }
      itParses("λx.y") {
        Abstraction(Variable("x"), Variable("y"))
      }
      itParses("λx.λy.x") {
        Abstraction(Variable("x"),
          Abstraction(Variable("y"),
            Variable("x")))
      }
      itParses("λx.(λy.x)") {
        Abstraction(Variable("x"),
          Abstraction(Variable("y"),
            Variable("x")))
      }
      itParses("yx") {
        Application(Variable("x"), Variable("y"))
      }

    }

    // it("doesn't parse the bad stuff") {
    //   itDoesntParse("(x")
    //   itDoesntParse("λxy")
    //   itDoesntParse("λx(.λ)y.x")
    // }

  }

}

