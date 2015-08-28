package rut

object Rut {

  def getDigits(n: Int, acc: List[Int] = Nil): List[Int] =
    if (n == 0) acc
    else        getDigits(n / 10, n % 10 :: acc)
  
  def checkDigit(rut: Int): Char = {
    val reversedDigits = getDigits(rut).reverse
    val factors = Stream.continually(2 to 7).flatten
    val s = reversedDigits.zip(factors).map { case (d, f) ⇒ d * f }.sum
    "0k987654321"(s % 11)
  }

  private val rutPattern = """(\d+)-([0-9Kk])""".r
  def isValid(rut: String): Boolean = rut match {
    case rutPattern(left, right) ⇒ checkDigit(left.toInt) == right.toLowerCase.head
    case _ ⇒ false
  }

}
