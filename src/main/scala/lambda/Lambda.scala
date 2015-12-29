package lambda

sealed trait Expression

case class Application(fn: Expression, arg: Expression) extends Expression {
  override def toString = s"($fn)($arg)"
}

case class Abstraction(param: Variable, expr: Expression) extends Expression {
  override def toString = s"λ$param.{$expr}"
}

case class Variable(name: String) extends Expression {
  override def toString = name
  def occursFreeIn(expr: Expression): Boolean = expr match {
    case Variable(y)                 ⇒ name == y
    case Application(e, f)           ⇒ occursFreeIn(e) || occursFreeIn(f)
    case Abstraction(Variable(y), e) ⇒ name != y && occursFreeIn(e)
  }
  def occursBoundIn(expr: Expression): Boolean = expr match {
    case Variable(_)                 ⇒ false
    case Application(e, f)           ⇒ occursBoundIn(e) || occursBoundIn(f)
    case Abstraction(Variable(y), e) ⇒ (name == y && occursFreeIn(e)) ||
                                       occursBoundIn(e)
  }
}



