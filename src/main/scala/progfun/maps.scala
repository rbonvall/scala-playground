package week6

object maps {
  val romanNumerals = Map("I" → 1, "V" → 5, "X" → 10)
  val capitalOfCountry: Map[String, String] = Map(
    "US" → "Washington",
    "Switzerland" → "Bern"
  )

  def main(args: Array[String]) = {
    // Maps act like functions:
    println(capitalOfCountry("US"))
    //println(capitalOfCountry("Andorra"))   // throws java.util.NoSuchElementException

    // get method is a better way of querying a map
    // (it doesn't fail when the key is missing):
    capitalOfCountry get "US"      foreach println
    capitalOfCountry get "Andorra" foreach println

    // Using pattern-matching:
    val c = capitalOfCountry get "Andorra" match {
       case Some(capital) => capital
       case None => "missing data"
    }
    println(c)
  }
}

