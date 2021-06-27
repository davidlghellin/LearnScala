package es.david.recursividad

object Fibo extends App {

  def fib1(n: Int): Int = n match {
    case 0 | 1 => n
    case _ => fib1(n - 1) + fib1(n - 2)
  }

  val t1 = System.nanoTime
  println(fib1(50))
  val duration = (System.nanoTime - t1) / 1e9d
  println(s"hemos tardado $duration")

  val fib: Stream[BigInt] = 0 #:: fib.scan(1: BigInt)(_ + _)

  // con esto memoization optimizamos tiempos ya que una vez calculada guarda ese valor
  val t2 = System.nanoTime

  def memo[A, R](f: A => R): A => R =
    new collection.mutable.WeakHashMap[A, R] {
      override def apply(a: A) = getOrElseUpdate(a, f(a))
    }

  val mfib = memo(fib)

  mfib(99) //res0: BigInt = 218922995834555169026
  println(mfib(99))
  val duration2 = (System.nanoTime - t2) / 1e9d
  println(s"hemos tardado $duration2")

  /*******/
  case class Memo[I <% K, K, O](f: I => O) extends (I => O) {
    import scala.collection.mutable.{Map => Dict}
    val cache = Dict.empty[K, O]
    override def apply(x: I) = cache getOrElseUpdate(x, f(x))
  }

  type ==>[I, O] = Memo[I, I, O]

  /******************************************/
  val fibMemo: Int ==> BigInt = Memo {
    case n@(0 | 1) => n
    case n => fibMemo(n - 1) + fibMemo(n - 2)
  }

  println(fibMemo(100))

}
