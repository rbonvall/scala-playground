package stuff

object Counter {

  def countMap[T](elements: Seq[T]): Map[T, Int] = {
    def iter(elements: Seq[T], acc: Map[T, Int]): Map[T, Int] = elements match {
      case Nil => acc
      case x :: xs =>
        iter(xs, acc + (x -> (1 + acc.getOrElse(x, 0))))
    }
    iter(elements, Map())
  }

}






