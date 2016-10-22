package expr

import org.scalatest.FunSpec
import org.scalatest.prop.TableDrivenPropertyChecks._

import fastparse.all._

class ExpressionsSpec extends FunSpec {
  import ExpressionParser._

  describe("excelString parser") {

    it("parses Excel strings") {
      val cases = Table(
        "input"   → "parsed"
      , "\"\""    → ""
      , "\"abc\"" → "abc"
      , "\" aa  bb  ccc  \"" → " aa  bb  ccc  "
      , "\" aa\"\"  bb c\"\"cc  \"" → " aa\"  bb c\"cc  "
      )
      forAll(cases) { case (input, parsed) ⇒
        val Parsed.Success(result, _) = excelString.parse(input)
        assert(result === StringLiteral(parsed))
      }
    }
  }

}

