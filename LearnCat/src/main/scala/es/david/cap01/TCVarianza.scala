package es.david.cap01

object TCVarianza {

  import cats.instances.int._
  import cats.instances.option._
  import cats.syntax.eq._

  val aComparacion = Option(2)  === Option(2)

  // varianza
  class Animal
  class Gato extends Animal

  // covarianza: subtipo es propagado to teh generaic type
  class Cage[+T]
  val cage:Cage[Animal] = new Cage[Gato]

  // contravariante
  class Vet[-T]
  val vet:Vet[Gato] = new Vet[Animal]
  // regla de oro: "TIENE un T" = covariante, "Actua como T" = contravariante

}
