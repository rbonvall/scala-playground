package lambda

sealed trait Expression
case class Variable(name: String) extends Expression {
  override def toString = name
}
case class Application(fn: Expression, arg: Expression) extends Expression {
  override def toString = s"($fn)($arg)"
}
case class Abstraction(param: Variable, expr: Expression) extends Expression {
  override def toString = s"λ$param.{$expr}"
}



