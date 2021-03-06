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

  // order is important here so "<=" does not get parsed as "<"
  def lessish: Parser[String] = "<=" | "<"
  def greatish: Parser[String] = ">=" | ">"

  def boundedInterval =
    number ~ lessish ~ name ~ lessish ~ number ^^ { case l ~ opL ~ _ ~ opR ~ r ⇒
      Interval(Some(Bound(l, opL contains "=")),
               Some(Bound(r, opR contains "=")))
    }

  def halfBoundedInterval =
  ( name ~ lessish ~ number ^^ { case _ ~ op ~ r ⇒
      Interval(None,
               Some(Bound(r, op contains "=")))
    }
  | name ~ greatish ~ number ^^ { case _ ~ op ~ l ⇒
      Interval(Some(Bound(l, op contains "=")),
               None)
    }
  | number ~ lessish ~ name ^^ { case l ~ op ~ _ ⇒
      Interval(Some(Bound(l, op contains "=")),
               None)
    }
  | number ~ greatish ~ name ^^ { case r ~ op ~ _ ⇒
      Interval(None,
               Some(Bound(r, op contains "=")))
    }
  )

}

