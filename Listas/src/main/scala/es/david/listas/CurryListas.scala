package es.david.listas

object CurryListas {
  //  https://docs.scala-lang.org/tour/currying.html

  /*
    veamos el ejemplo que se muestra en la documentacion
    filtraremos los valores de una lista por el modulo
  */

  def moduloN(n: Int, x: Int): Boolean = ((x % n) == 0)
  val modNCurry: Int => Int => Boolean = (moduloN _).curried

  // definimos la formula del modulo como funcion currificada
  def modN(n: Int)(x: Int): Boolean = ((x % n) == 0)

  // ahora declaramos la funcion parcial de los modulos 2 y 3
  val mod2: Int => Boolean = modN(2)
  val mod3: Int => Boolean = modN(3)

  // ahora definimos nuestro propio filter recursivamente como nos dice la docu
  def filter(xs: List[Int], p: Int => Boolean): List[Int] =
    if (xs.isEmpty) xs
    else if (p(xs.head)) xs.head :: filter(xs.tail, p)
    else filter(xs.tail, p)

  def filterPatter(xs: List[Int], p: Int => Boolean): List[Int] = (xs, p) match {
    case (Nil, _) => xs
    case _ if (p(xs.head)) => xs.head :: filter(xs.tail, p)
    case _ => filter(xs.tail, p)
  }

  // https://lukajcb.github.io/blog/scala/2016/03/08/a-real-world-currying-example.html
  // funcion que currifica cualquier funcion binaria
  def curryBinaryOperator[A](operator: (A, A) => A): A => (A => A) = {

    def curry(a: A): A => A = {
      (b: A) => operator(a, b)
    }

    curry
  }

  def add(a: Int, b: Int) = a + b // (Int, Int) => Int
  def multiply(a: Int, b: Int) = a * b // (Int, Int) => Int

  val addCurried = curryBinaryOperator(add) // Int => (Int => Int)
  val multiplyCurried = curryBinaryOperator(multiply) // Int => (Int => Int)
}
