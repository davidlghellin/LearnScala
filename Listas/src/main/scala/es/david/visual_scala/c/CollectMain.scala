package es.david.visual_scala.c

object CollectMain extends App {
  val listInt = List(2, 3, 4, 5)

  /*
  collect construye una colección utilizando los resultados
  de aplicar la función parcial f a aquellos elementos para los cuales f está definida,
  descartando el resto de elementos.

  Podria verse como un filter + map
   */
  val esPar: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
    def apply(x: Int): Int = x

    def isDefinedAt(x: Int) = x % 2 == 0
  }

  val listaPares = listInt.collect { case n if n % 2 == 0 => n }
  val listaPares2 = listInt.collect(esPar)
  println(listaPares)
  println(listaPares2)


  //http://allaboutscala.com/tutorials/chapter-8-beginner-tutorial-using-scala-collection-functions/scala-collect-function/
  println("Step 1: How to initialize a Sequence which contains donut names and prices")
  val donutNamesandPrices: Seq[Any] = Seq("Plain Donut", 1.5, "Strawberry Donut", 2.0, "Glazed Donut", 2.5)
  println(s"Elements of donutNamesAndPrices = $donutNamesandPrices")


  println("\nStep 2: How to use collect function to cherry pick all the donut names")
  val donutNames: Seq[String] = donutNamesandPrices.collect { case name: String => name }
  println(s"Elements of donutNames = $donutNames")


  println("\nStep 3: How to use collect function to cherry pick all the donut prices")
  val donutPrices: Seq[Double] = donutNamesandPrices.collect { case price: Double => price }
  println(s"Elements of donutPrices = $donutPrices")
}
