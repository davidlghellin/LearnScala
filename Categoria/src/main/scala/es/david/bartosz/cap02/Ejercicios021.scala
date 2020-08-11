package es.david.bartosz.cap02

import java.util.concurrent.TimeUnit

// TODO
object Ejercicios021 extends App {
  def memoize(f: Int => Int): Int => Int = {
    fSleep(3)
    f
  }

  private def fSleep(n: Int): Int = {
    TimeUnit.SECONDS.sleep(10);
    n
  }


  //  https://stackoverflow.com/questions/46696109/execute-function-only-once-and-cache-value-in-scala
  /** Memoize a pure function `f(A): R`
   *
   * @constructor Create a new memoized function.
   * @tparam A Type of argument passed to function.
   * @tparam R Type of result received from function.
   * @param f Pure function to be memoized.
   */
  final class Memoize1[A, R](f: A => R) extends (A => R) {

    // Cached function call results.
    private val result = scala.collection.mutable.Map.empty[A, R]

    /** Call memoized function.
     *
     * If the function has not been called with the specified argument value, then the
     * function is called and the result cached; otherwise the previously cached
     * result is returned.
     *
     * @param a Argument value to be passed to `f`.
     * @return Result of `f(a)`.
     */
    def apply(a: A) = synchronized(result.getOrElseUpdate(a, f(a)))
  }

  /** Memoization companion */
  object Memoize1 {

    /** Memoize a specific function.
     *
     * @tparam A Type of argument passed to function.
     * @tparam R Type of result received from function.
     * @param f Pure function to be memoized.
     */
    def apply[A, R](f: A => R) = new Memoize1(f)
  }

  val esperar = Memoize1(memoize((x) => x))
  // Espera los 10 segundos
  println(esperar)
  // No espera
  println(esperar)
}
