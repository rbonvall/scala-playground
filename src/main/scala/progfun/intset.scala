package week7

abstract class IntSet {
  def contains(x: Int): Boolean
  def incl(x: Int): IntSet
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = NonEmpty(x, Empty, Empty)
  def union(other: IntSet): IntSet = other
}

case class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if      (x < elem) left contains x
    else if (x > elem) right contains x
    else               true

  def incl(x: Int): IntSet =
    if      (x < elem) NonEmpty(elem, left incl x, right)
    else if (x > elem) NonEmpty(elem, left,        right incl x)
    else               this

  def union(other: IntSet): IntSet =
    (left union (right union other)) incl elem
}

// Three laws:
//
// • Empty contains x = false              ∀ x
// • (s incl x) contains x = true          ∀ x
// • (s incl x) contains y = s contains y  if x ≠ y
