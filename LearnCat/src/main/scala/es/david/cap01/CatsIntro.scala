package es.david.cap01

object CatsIntro {

  // EQ
  //val unaComparacion = 2 == "cadena"

  // part1 -type class import

  import cats.Eq

  // part2 - import TC instances para los tipos que necesitas
  import cats.instances.int._

  // part 3 - use el api de TC
  val intEq = Eq[Int]
  val aTypeSafeComparasioon = intEq.eqv(2, 3)
  //val noCompila = intEq.eqv(2, "aa")

  // part4 - use extension metodos (si fuera aplicable)

  import cats.syntax.eq._

  val otraComparacionSegura = 2 === 3
  //val otraComparacionMal = 2 === ""
  // extension solo visible si esta presente el correcto

  // part5 - extender las TC para los wrapers..

  import cats.instances.list._

  val comparacionDeListas = List(2) === List(2)

  // part6 crear instancias de TC de clases personalizadas
  case class CocheJuguetes(modelo: String, precio: Float)

  implicit val toyCarEq: Eq[CocheJuguetes] = Eq.instance((car1, car2) =>
    car1.precio == car2.precio
  )
  val compareCoches = CocheJuguetes("", 1) === CocheJuguetes("", 1) // true
}
