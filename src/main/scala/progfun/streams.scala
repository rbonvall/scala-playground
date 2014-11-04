package week7

object streams {
  def streamRange(lo: Int, hi: Int): Stream[Int] =
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo + 1, hi))

  def from(n: Int): Stream[Int] = n #:: from(n + 1)

  val nats = from(0)
  val m4s = nats map { _ * 4 }

  def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail filter { _ % s.head != 0})
  val primes = sieve(from(2))

  def sqrtStream(x: Double): Stream[Double] = {
    def improve(guess: Double) = (guess + x / guess) / 2
    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses
  }
  def isGoodEnough(guess: Double, x: Double) =
    math.abs((guess * guess - x) / x) < 1e-4

  val sqrt4 = sqrtStream(4).filter(isGoodEnough(_, 4)).head
}

