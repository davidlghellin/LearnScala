package es.david.scalaz.mortals.cap02

object ForComprehension extends App {
  val a, b, c = Option(1)

  val v1: Option[Int] = for {i <- a
                             j <- b
                             k <- c
                             } yield (i + j + k)

  val v2: Option[Int] = a.flatMap {
    i =>
      b.flatMap {
        j =>
          c.map {
            k => i + j + k
          }
      }
  }
  println(v1)
  println(v2)

  // como vamos a ver trabajamos a nivel de lo que hay dentro del option
  val la, lb, lc: Option[List[Int]] = Option(List(1, 2, 3, 4))

  val v1l: Option[List[Int]] = for {i <- la
                                    j <- lb
                                    k <- lc
                                    } yield (i ::: j ::: k)

  println(v1l)
  val v2l: Option[List[Int]] = la.flatMap {
    i =>
      lb.flatMap {
        j =>
          lc.map {
            k => i ::: j ::: k
          }
      }
  }
  println(v2l)
  // como podemos ver
  // La regla general es que cada <- del for es llamado generador
  // es una flatMapllamada anidada ,
  // con el generador final que contiene el map y el cuerpo del yield.


  // Podemos crear asignaciones en el forExpresion, no hace falta el val
  // siempre podemos usar las variables, despues de haberlas creado con la <-
  val aa: Option[Int] = for {
    i <- a
    j <- b
    ij = (i + j) * 10
    k <- c
  } yield (ij + k)
  println(aa)

  // FILTROS
  // podemos poder condicionales 
  val resFilter: Option[Int] = for {
    i <- a
    j <- b
    k <- c
    if i > j
  } yield (i + j + k)
  println(resFilter) // como la condicion no se cumple -> None

  // For Each
  // si usamos efectos de lado el compilador usa foreach
  for {i <- a
       j <- b
       } println(s"$i $j")
  a.foreach { i => b.foreach { j => println(s"$i $j") } }

  // en estos for comprehension, nos dice resultado si todo_ va bien
  // si alguno tiene un valor None, se produce un cortocircuito pero no nos dice que salio mal
  // Si queremos un control más detallado usaremos Either, que nos dará en Left el cortocircuito
  val aEither = Right(1)
  val bEither = Right(2)
  val cEither: Either[String, Int] = Left("sorry, no c")
  val resEither = for {
    i <- aEither
    j <- bEither
    k <- cEither
  } yield (i + j + k)
  println(resEither)
}
