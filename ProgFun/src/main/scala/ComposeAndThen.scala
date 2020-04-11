object ComposeAndThen extends App {
  val suma1: Int => Int = { num => num + 1 }
  val multiplica2: Int => Int = _ * 3
  val par: Int => Boolean = number => number % 2 == 0

  val multipli: Int => Int => Int = { a => b => a * b }

  val suma5: Int => Int => Int => Int => Int => Int = { a => b => c => d => e => a + b + c + d + e }

  val resultAndThen: Int => Boolean = suma1 andThen multiplica2 andThen par
  val resultCompose: Int => Boolean = par compose multiplica2 compose suma1

  val a: Boolean = resultAndThen(2)
  println(s"el resultado es: ${a}")

  println(s"el resultado es: ${resultAndThen(3)}")
  println(s"el resultado es: ${resultCompose(4)}")

  val f: String => Int = s => s.length
  val g: Int => Int = i => i * 2

  val h = f.andThen(g)
  println(h("aaa"))

  def suma(a: Int)(b: Int): Int = {
    a + b
  }

  val sumaF: Int => Int => Int = { a => b => a + b }
  val miSuma1 = sumaF(1)(2)
  println(s"Suma: ${sumaF(1)}")
  println(s"Suma: $miSuma1")

  val repite: Int => String => String = { num => cadena => cadena * num }
  val repite3: String => String = repite(3)
  println(s"La cadena repetidas: ${repite(3)("x")}")
  println(s"La cadena repetida3: ${repite3("y")}")
}
