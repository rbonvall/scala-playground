package xml

import scala.xml.{Elem, Text, Node}

object Populate {

  implicit class XsplittableString(s: String) {
    def x = if (s.isEmpty) Nil else s.split("/").toList
  }

  def insert[T](tree: Elem, path: String, value: T): Elem = insert(tree, path.x, value)

  def insert[T](tree: Elem, path: List[String], value: T): Elem = {
    path match {
      case Nil           ⇒ ???
      case first :: rest ⇒
        if (tree.label != first) tree
        else if (rest.isEmpty)   tree.copy(child = Text(value.toString))
        else {
          val newChildren = tree.child.map {
            case child: Elem ⇒ insert(child, rest, value)
            case x ⇒ x
          }
          tree.copy(child = newChildren)
        }
    }
  }

  def createElem[T](path: List[String], value: T): Elem = path match {
    case Nil           ⇒ ???
    case first :: Nil  ⇒ <foo>{value.toString         }</foo>.copy(label = first)
    case first :: rest ⇒ <foo>{createElem(rest, value)}</foo>.copy(label = first)
  }

  def splitPathAtInsertionPoint(tree: Elem, path: List[String]): (List[String], List[String]) = {
    splits(path)
      .takeWhile { case (left, _) ⇒ containsPath(tree, left) }
      .last
  }

  def containsPath(tree: Elem, path: List[String]): Boolean = {
    path match {
      case Nil           ⇒ true
      case first :: Nil  ⇒ tree.label == first
      case first :: rest ⇒ tree.label == first &&
        tree.child.exists {
          case e: Elem ⇒ containsPath(e, rest)
          case _       ⇒ false
        }
    }
  }

  def splits[T](xs: List[T]): Seq[(List[T], List[T])] = (0 to xs.length).map(xs.splitAt)

  def prefixes[T](xs: List[T]): List[List[T]] = xs.scanLeft(List.empty[T]) { _ :+ _ }.tail

  def nodeToElem(n: Node): Elem = Elem(n.prefix, n.label, n.attributes, n.scope, true, n.child: _*)

}
