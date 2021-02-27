package es.david.derivacionClasesTipos

object ClasesTipos01 extends App {

  // Una clase de tipo es un rasgo parametrizado que representa
  // algún tipo de funcionalidad general que nos gustaría aplicar a una amplia gama de tipos

  // Turn a value of type A into a row of cells in a CSV file:
  trait CsvEncoder[A] {
    def encode(value: A): List[String]
  }

  // Implementamos nuestra clase de tipos con instancias para cada tipo que nos importa.
  // Si queremos que las instancias estén automáticamente dentro del alcance,
  // podemos colocarlas en el objeto complementario de la clase de tipo. De lo contrario,
  // podemos colocarlos en un objeto de biblioteca separado para que el usuario los importe manualmente
  case class Employee(name: String, number: Int, manager: Boolean)

  // CsvEncoder instance for the custom data type:
  implicit val employeeEncoder: CsvEncoder[Employee] =
    new CsvEncoder[Employee] {
      def encode(e: Employee): List[String] =
        List(
          e.name,
          e.number.toString,
          if (e.manager) "yes" else "no"
        )
    }

  def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
    values.map(value => enc.encode(value).mkString(",")).mkString("\n")

  val employees: List[Employee] = List(
    Employee("Bill", 1, true),
    Employee("Peter", 2, false),
    Employee("Milton", 3, false)
  )
  val resEmployed: String = writeCsv(employees)
  println(resEmployed)

  println()

  //Podemos usar writeCsvcon cualquier tipo de datos que queramos, siempre que tengamos un CsvEncoderalcance implícito correspondiente
  case class IceCream(name: String, numCherries: Int, inCone: Boolean)

  object Helado{
    implicit val iceCreamEncoder: CsvEncoder[IceCream] =
      new CsvEncoder[IceCream] {
        def encode(i: IceCream): List[String] =
          List(
            i.name,
            i.numCherries.toString,
            if (i.inCone) "yes" else "no"
          )
      }
  }
  implicit val iceCreamEncoder: CsvEncoder[IceCream] =
    new CsvEncoder[IceCream] {
      def encode(i: IceCream): List[String] =
        List(
          i.name,
          i.numCherries.toString,
          if (i.inCone) "yes" else "no"
        )
    }

  val iceCreams: List[IceCream] = List(
    IceCream("Sundae", 1, false),
    IceCream("Cornetto", 0, true),
    IceCream("Banana Split", 0, false)
  )

  println(writeCsv(iceCreams))


  implicit def pairEncoder[A, B](
                                  implicit
                                  aEncoder: CsvEncoder[A],
                                  bEncoder: CsvEncoder[B]
                                ): CsvEncoder[(A, B)] =
    new CsvEncoder[(A, B)] {
      def encode(pair: (A, B)): List[String] = {
        val (a, b) = pair
        aEncoder.encode(a) ++ bEncoder.encode(b)
      }
    }

  println(writeCsv(employees zip iceCreams))
}
