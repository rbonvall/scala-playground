package rectangles

//             w     (x1, y1)
//        +----------+
//      h |          |
//        +----------+
// (x0, y0)

case class Rectangle(val x0: Double,
                     val y0: Double,
                     val width: Double,
                     val height: Double) {
  val x1 = x0 + width
  val y1 = y0 + height
  val p0 = (x0, y0)
  val p1 = (x1, y1)
  val center = (x0 + width/2, y0 + height/2)

  def hMove(dx: Double) = copy(x0 = x0 + dx)
  def vMove(dy: Double) = copy(y0 = y0 + dy)
  def trimRight (dx: Double) = copy(width = width - dx)
  def trimTop   (dy: Double) = copy(height = height - dy)
  def trimLeft  (dx: Double) = trimRight(dx).hMove(dx)
  def trimBottom(dy: Double) = trimTop  (dy).vMove(dy)
}

