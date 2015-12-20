package geom

case class Point(x: Double, y: Double) {
  import Math.{atan2, PI ⇒ π, sqrt ⇒ √}

  def +(p: Point) = Point(x + p.x, y + p.y)
  def -(p: Point) = Point(x - p.x, y - p.y)
  def *(s: Double) = Point(s * x, s * y)
  def θ = (atan2(y, x) + 2 * π) % (2 * π)

  def distanceTo(p: Point): Double = {
    val δx = p.x - x
    val δy = p.y - y
    √(δx * δx + δy * δy)
  }

}

object Point {
  val π = Math.PI
}

object PointTupleConversions {
  implicit def pointToTuple(p: Point): (Double, Double) = (p.x, p.y)
  implicit def tupleToPoint(t: (Double, Double)): Point = Point(t._1, t._2)
}
