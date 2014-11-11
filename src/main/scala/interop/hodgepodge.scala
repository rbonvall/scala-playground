// Adventures in Scala-Java interoperability

package interop

case class Date(year: Int, month: Int, day: Int) {
  val days = Array(0, 31, 28, 31, 30,
                      31, 30, 31, 31,
                      30, 31, 30, 31)
  def next = (month, day) match {
    // TODO: support leap years
    case (12, 31)               ⇒ Date(year + 1,      1,     1)
    case (m, d) if d == days(m) ⇒ Date(year,      m + 1,     1)
    case (m, d)                 ⇒ Date(year,      m,     d + 1)
  }
  def < (other: Date) =
    if      (this.year  != other.year)  this.year  < other.year
    else if (this.month != other.month) this.month < other.month
    else                                this.day   < other.day
  def - (other: Date): Int = {
    // TODO: tail recursion
    if      (this < other)  -(other - this)
    else if (this == other) 0
    else                    1 + (this - other.next)
  }
  override def toString =
    List(year, month, day) mkString "/"
}

object HodgePodge {

}
