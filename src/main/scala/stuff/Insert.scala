package stuff

object InsertIntoOrderedList {

  def insert1[T <% Ordered[T]](list: List[T], elem: T): List[T] = list match {
    case Nil => List(elem)
    case x :: xs =>
      if (elem < x) elem :: x :: xs
      else          x :: insert1(xs, elem)
  }

}