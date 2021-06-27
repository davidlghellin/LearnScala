package es.david.recursividad

object IsPrime extends App {

  val t1 = System.nanoTime

  def isPrime(num: Int): Boolean =
    2 to (num - 1) forall (x => num % x != 0)

  println(isPrime(1000))

  val duration = (System.nanoTime - t1) / 1e9d
  println(s"hemos tardado $duration")
  val t2 = System.nanoTime

  def memoizedIsPrime: Int => Boolean = {
    def isPrime(num: Int): Boolean = {
      2 to (num - 1) forall (x => num % x != 0)
    }

    val cache = collection.mutable.Map.empty[Int, Boolean]

    num =>
      cache.getOrElse(num, {
        print(s"\n Calling isPrime since input ${num} hasn't seen before and caching the output")
        cache update(num, isPrime(num))
        cache(num)
      })
  }

  val isPrimeM = memoizedIsPrime
  println(isPrimeM(1000))
  val duration2 = (System.nanoTime - t2) / 1e9d
  println(s"hemos tardado $duration2")
}
