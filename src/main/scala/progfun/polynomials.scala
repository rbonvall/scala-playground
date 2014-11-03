package week6

object polynomials {
  class Poly(val partialTerms: Map[Int, Double]) {

    // Alternate constructor
    def this(bindings: (Int, Double)*) = this(bindings.toMap)

    // def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))
    // def adjust(term: (Int, Double)): (Int, Double) = {
    //   val (exp, coeff) = term
    //   // terms get exp match {
    //   //   case Some(c) ⇒ exp → (c + coeff)
    //   //   case None    ⇒ exp → coeff
    //   // }
    //   exp → (coeff + terms(exp))
    // }

    def + (other: Poly) = new Poly((other.terms foldLeft terms)(addTerm))
    def addTerm(terms: Map[Int, Double], term: (Int, Double)) = {
      val (exp, coeff) = term
      //terms + (exp → (terms.getOrElse(exp, 0.0) + coeff))
      terms + (exp → (terms(exp) + coeff))
    }

    val terms = partialTerms withDefaultValue 0.0
    override def toString =
      (for ((exp, coeff) ← terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }
}
