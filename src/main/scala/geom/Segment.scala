package geom

case class Segment(start: Point, end: Point) {
  def length = start distanceTo end
  def contains(p: Point) = (start distanceTo p) + (p distanceTo end) == length
}

object SegmentTupleConversions {
  implicit def segmentToTuple(s: Segment): (Point, Point) = (s.start, s.end)
  implicit def tupleToSegment(t: (Point, Point)): Segment = Segment(t._1, t._2)
}
