package lambda

import scala.util.parsing.combinator._

object Parser extends RegexParsers {

  lazy val expression: Parser[Expression] = variable | bracketed | abstraction | application
  lazy val variable: Parser[Variable] = "[a-zA-Z]".r ^^ Variable
  lazy val bracketed: Parser[Expression] = "(" ~> expression <~ ")"
  lazy val application: Parser[Application] =
    expression ~ expression ^^ { case e ~ f ⇒ Application(e, f) }
  lazy val abstraction: Parser[Abstraction] =
    "λ" ~ variable ~ "." ~ expression ^^ { case _ ~ x ~ _ ~ e ⇒ Abstraction(x, e) }

  def apply(code: String) = parseAll(expression, code)

}
