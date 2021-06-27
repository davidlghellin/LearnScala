package es.david.dia04

import scalaz.Scalaz._


object LeyesFunctorScalaZ extends App {
  /*
  Se espera que todos los functores exhiban ciertos tipos de propiedades y comportamientos similares a los de los functores.
  */
  /*La primera ley del functor establece que si mapeamos la función id sobre un functor,
  el functor que obtenemos debe ser el mismo que el functor original.
 */
  println(List(1, 2, 3) map { identity } assert_=== List(1, 2, 3))
  /*
  La segunda ley dice que componer dos funciones y luego mapear la función resultante sobre un functor debe ser lo mismo
   que mapear primero una función sobre el functor y luego mapear la otra.
   */
  val suma: Int => Int = a => a + 1
  val mult: Int => Int = a => a * 3
  val multSuma = mult.andThen(suma)

  val res= (List(1, 2, 3) map {(_: Int) * 3} map {(_: Int) + 1})

  println((List(1, 2, 3) map {{(_: Int) * 3} map {(_: Int) + 1}})
    assert_=== res)

  println((List(1, 2, 3) map {{mult(_)} map {suma(_)}})
    assert_=== res)

  println((List(1, 2, 3) map {multSuma(_)})
    assert_=== res)
  // estas leyes las tenemos que cumplir y debemos testearlas FunctorLaw


  // TODO ver https://eed3si9n.com/learning-scalaz/Functor+Laws.html

}
