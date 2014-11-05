package condparse

import scala.util.parsing.combinator._

// trait Parser[+A] extends (Stream[Character] ⇒ Result[A])
// sealed trait Result[+A]
// case class Success[+A](value: A, rem: Strem[Character]) extends Result[A]
// case class Failure(msg: String) extends Result[Nothing]

object CondParser extends RegexParsers {

  def name: Parser[String] = """[A-Za-z]+""".r
  def number: Parser[Double] = """[+-]?\d+(\.\d*)?""".r ^^ { _.toDouble }
  def nameList: Parser[Seq[String]] = repsep(name, ",")

}

