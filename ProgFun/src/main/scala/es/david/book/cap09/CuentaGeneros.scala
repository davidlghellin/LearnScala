package es.david.book.cap09

object CuentaGeneros extends App {

  case class Persona(n: String, genero: Genero)

  val gen1 = Persona("David", Hombre)
  val gen2 = Persona("lola", Mujer)
  val gen3 = Persona("David", Hombre)
  val lista = List(gen1, gen2, gen3)

  val cuentaGeneros: List[Persona] => Genero => Int = list => g => {
    list match {
      case Nil => 0
      case x :: xs if x.genero == g => cuentaGeneros(xs)(g) + 1
      case _ :: xs => cuentaGeneros(xs)(g)
    }
  }
  println(cuentaGeneros(lista)(Hombre))
}

sealed trait Genero

final case object Hombre extends Genero

final case object Mujer extends Genero
