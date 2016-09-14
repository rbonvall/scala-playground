package polynomials

case class Poly private(coefs: List[Double]) {
  require(!coefs.lastOption.contains(0))
  def apply(x: Double): Double = coefs.foldRight(0.0) { (a, sum) ⇒ x * sum + a }
  def pretty: String = if (coefs.isEmpty) "0" else
    coefs.zipWithIndex
      .filter { case (a, _) ⇒ a != 0 }
      .map {
        case ( 1, k) if k != 0 ⇒ ("+", k)
        case (-1, k) if k != 0 ⇒ ("-", k)
        case ( a, k) ⇒ (f"$a%+f".replaceAll("[.]?0*$", ""), k)
      }
      .map {
        case (a, 0)                      ⇒ a
        case (a, 1)                      ⇒ s"${a}x"
        case (a, k) if 2 to 9 contains k ⇒ s"${a}x${Poly.sup(k)}"
        case (a, k)                      ⇒ s"${a}x^$k"
      }
      .mkString
      .replaceAll("^[+]", "")
  override def toString = s"Poly($pretty)"
  lazy val degree: Int = coefs.length - 1

  def padAndZipWith(that: Poly)(op: (Double, Double) ⇒ Double) = {
    val newLength = (this.degree max that.degree) + 1
    val pairs = this.coefs.padTo(newLength, 0.0) zip that.coefs.padTo(newLength, 0.0)
    Poly.fromCoefficients(pairs.map { case (a, b) ⇒ op(a, b) })
  }
  def +(that: Poly): Poly = padAndZipWith(that) { _ + _ }
  def -(that: Poly): Poly = padAndZipWith(that) { _ - _ }
  def *(z: Double): Poly = Poly.fromCoefficients(this.coefs.map(_ * z))
  def unary_- = this * -1.0
}

object Poly {
  def normalizeCoefficients(coefs: List[Double]) = coefs.reverse.dropWhile(_ == 0).reverse
  def apply(args: Double*): Poly = new Poly(normalizeCoefficients(args.toList))
  def fromCoefficients(coeffs: List[Double]): Poly = apply(coeffs: _*)
  val sup = "⁰¹²³⁴⁵⁶⁷⁸⁹"
}

