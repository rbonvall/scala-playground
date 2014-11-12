package dates

object dateStuff {
  val days = Array(0, 31, 28, 31, 30,
                      31, 30, 31, 31,
                      30, 31, 30, 31)
  def isLeap(year: Int): Boolean =
    year %   4 == 0 &&
    year % 100 != 0 ||
    year % 400 == 0
}

case class Date(year: Int, month: Int, day: Int) {
  import dateStuff._
  def next = (month, day) match {
    case (12, 31)                 ⇒ Date(year + 1,      1,     1)
    case ( 2, 28) if isLeap(year) ⇒ Date(year,          2,    29)
    case ( 2, 29) if isLeap(year) ⇒ Date(year,          3,     1)
    case ( m,  d) if d == days(m) ⇒ Date(year,      m + 1,     1)
    case ( m,  d)                 ⇒ Date(year,      m,     d + 1)
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
