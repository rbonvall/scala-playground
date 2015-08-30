package rut

object Rut {

  def getDigits(n: Int, acc: List[Int] = Nil): List[Int] =
    if (n == 0) acc
    else        getDigits(n / 10, n % 10 :: acc)
  
  // def checkDigit(rut: Int): Char = {
  //   val reversedDigits = getDigits(rut).reverse
  //   val factors = Stream.continually(2 to 7).flatten
  //   val pairs = reversedDigits zip factors
  //   val s = pairs.map { case (d, f) ⇒ d * f }.sum
  //   "0k987654321"(s % 11)
  // }

  def checkDigit(rut: Int) = "0k987654321" {
    getDigits(rut)
      .reverse
      .zip(Stream.continually(2 to 7).flatten)
      .map { case (d, f) ⇒ d * f }
      .sum % 11
  }

  private val rutPattern = """(\d+)-([0-9Kk])""".r
  def isValid(rut: String): Boolean = rut match {
    case rutPattern(left, right) ⇒ checkDigit(left.toInt) == right.toLowerCase.head
    case _ ⇒ false
  }

}
