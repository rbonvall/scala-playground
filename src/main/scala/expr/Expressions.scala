package expr

import fastparse.all._

sealed trait Expr
case class StringLiteral(content: String) extends Expr

object ExpressionParser {

  val excelString = P("\"" ~ (CharPred(_ != '"') | "\"\"").rep.! ~ "\"")
    .map { s ⇒ StringLiteral(s.replace("\"\"", "\"")) }

}
