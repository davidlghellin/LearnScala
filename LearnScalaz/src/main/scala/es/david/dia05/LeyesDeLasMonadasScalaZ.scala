package es.david.dia05

import scalaz.Monad
import scalaz.Scalaz._

object LeyesDeLasMonadasScalaZ extends App {
  /*
  Identidad de la izquierda
  La primera ley de mónadas establece que si tomamos un valor,
  lo ponemos en un contexto predeterminado con returny luego lo alimentamos a una función usando >>=, es lo mismo que simplemente tomar el valor y aplicarle la función.
   */
  println((Monad[Option].point(3) >>= { x => (x + 100000).some }))
  println((Monad[Option].point(3) >>= { x => (x + 100000).some })
    assert_=== 3 |> { x => (x + 100000).some })

  /*
  Identidad correcta
  La segunda ley establece que si tenemos un valor monádico y lo usamos >>=para alimentarlo return,
  el resultado es nuestro valor monádico original.
   */
  println("move on up".some  flatMap {Monad[Option].point(_)})
  println(("move on up".some flatMap {Monad[Option].point(_)}) assert_=== "move on up".some)
  println()
  /*
  Asociatividad
  La ley final de las mónadas dice que cuando tenemos una cadena de aplicaciones de función monádica con >>=,
  no debería importar cómo están anidadas.
   */
  println()
}
