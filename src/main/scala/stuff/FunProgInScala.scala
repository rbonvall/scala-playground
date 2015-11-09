package stuff

object FunProgInScala {

  @annotation.tailrec
  def isSorted[A](as: Array[A], gt: (A, A) ⇒ Boolean): Boolean =
    as.length < 2 || (gt(as(1), as(0)) && isSorted(as.tail, gt))

  def partial1[A, B, C](a: A, f: (A, B) ⇒ C): B ⇒ C =
    b ⇒ f(a, b)

  def curry[A, B, C](f: (A, B) ⇒ C): A ⇒ (B ⇒ C) =
    a ⇒ (b ⇒ f(a, b))

  def uncurry[A, B, C](f: A ⇒ B ⇒ C): (A, B) ⇒ C =
    (a, b) ⇒ f(a)(b)

  def compose[A, B, C](f: B ⇒ C, g: A ⇒ B): A ⇒ C =
    x ⇒ f(g(x))

}
