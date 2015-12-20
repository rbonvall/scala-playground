package geom

case class Polygon(points: Seq[Point]) {
  require(points.length >= 3)

  def segments = points
    .zip(points.tail :+ points.head)
    .map { case (p, q) ⇒ Segment(p, q) }

  def perimeter = segments.map(_.length).sum

}
