package condparse

import scala.util.parsing.combinator._

object CondParser extends RegexParsers {

  def name: Parser[String] = """[A-Za-z]+""".r
  def number: Parser[Double] = """[+-]?\d+(\.\d*)?""".r ^^ { _.toDouble }
  def nameList: Parser[Seq[String]] = rep1sep(name, ",")
  def negatedNameList: Parser[Seq[String]] = "NOT(" ~> nameList <~ ")"

}

