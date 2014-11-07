package condparse

import scala.util.parsing.combinator._

object CondParser extends RegexParsers {

  case class Bound(value: Double, closed: Boolean)
  case class Interval(left: Option[Bound], right: Option[Bound]) {
    def this(left: Double, leftClosed: Boolean, right: Double, rightClosed: Boolean) =
      this(Some(Bound(left, leftClosed)), Some(Bound(right, rightClosed)))
  }

  def name: Parser[String] = """[A-Za-z]+""".r
  def number: Parser[Double] = """[+-]?\d+(\.\d*)?""".r ^^ { _.toDouble }
  def nameList: Parser[Seq[String]] = rep1sep(name, ",")
  def negatedNameList: Parser[Seq[String]] = "NOT(" ~> nameList <~ ")"

}

