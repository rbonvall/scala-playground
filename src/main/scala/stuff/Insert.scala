package stuff

object InsertIntoOrderedList {

  def insert0[T <% Ordered[T]](list: List[T], elem: T): List[T] = list match {
    case Nil => List(elem)
    case x :: xs =>
      if (elem < x) elem :: x :: xs
      else          x :: insert1(xs, elem)
  }

  def insert1[T <% Ordered[T]](list: List[T], elem: T): List[T] = {
    val (pre, post) = list span {_ < elem}
    pre ::: elem :: post
  }

  // Tail recursive but relies on appending at the end.
  def insert2[T <% Ordered[T]](list: List[T], elem: T): List[T] = {
    def iter(list: List[T], acc: List[T]): List[T] = list match {
      case Nil => acc ++ List(elem)
      case x :: xs =>
        if (elem < x) acc ::: elem :: x :: xs
        else          iter(xs, acc ++ List(x))
    }
    iter(list, Nil)
  }

  def insert3[T <% Ordered[T]](list: List[T], elem: T): List[T] = {
    list.foldRight(List[T]()) { (x, acc) =>
      if (elem < x) x :: acc
      else          x :: elem :: acc
    }
  }

}
