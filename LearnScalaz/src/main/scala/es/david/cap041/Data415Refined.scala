package es.david.cap041

import java.util.regex.Pattern

import eu.timepit.refined
import eu.timepit.refined.api.{Refined, Validate}

object Data415Refined extends App {
  // importamos la siguiente libreria
  // libraryDependencies += "eu.timepit" %% "refined-scalaz" % "0.9.2"

  // Todos los tipos con dos parametros se pueden escribir en notación infija
  //  Either[String,Int] es lo mismo que  String Either Int

  import refined.collection.NonEmpty
  import refined.numeric.Positive

  final case class Person(
                           name: String Refined NonEmpty,
                           age: Int Refined Positive
                         )

  import refined.refineV

  println(refineV[NonEmpty](""))
  println(refineV[NonEmpty]("David"))

  // si importamos los siguiente, podremos construir valores validos y
  // tener un error si proveemos algo que no lo cumple las condiciones en tiempo de compilación

  import refined.auto._

  val sam: String Refined NonEmpty = "sam"
  // esto lo vi en un meetup de scala, si compila funciona

  import refined.W
  import refined.boolean.And
  import refined.collection.MaxSize

  type Name = NonEmpty And MaxSize[W.`10`.T]

  final case class PersonW(
                            name: String Refined Name,
                            age: Int Refined Positive
                          )

  //  PersonW("", -9) // no compilaria
  // Podemos crear nuevas normas con expreisones regulares de java
  sealed abstract class UrlEncoded
  object UrlEncoded {
    private[this] val valid: Pattern =
      Pattern.compile("\\A(\\p{Alnum}++|[-.*_+=&]++|%\\p{XDigit}{2})*\\z")

    implicit def urlValidate: Validate.Plain[String, UrlEncoded] =
      Validate.fromPredicate(
        s => valid.matcher(s).find(),
        identity,
        new UrlEncoded {}
      )
  }
}
