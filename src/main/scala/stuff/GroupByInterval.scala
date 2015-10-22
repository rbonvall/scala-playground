package stuff

object GroupByInterval {
  def apply(values: List[Int]) = iter(values, Nil)

  def iter(values: List[Int], acc: List[(Int, Int)]): List[(Int, Int)] = values match {
    case Nil ⇒ acc.reverse
    case x :: xs if acc.isEmpty ⇒ iter(xs, (x, x) :: Nil)
    case x :: xs ⇒
      val (from, to) = acc.head
      if (x == to + 1) iter(xs, (from, x) :: acc.tail)
      else             iter(xs, (x, x)    :: acc)
  }
}
