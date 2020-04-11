package es.david.cap041

object Data417 {
  // La complejidad de datos es el recuento de cuantos valores pueden existir
  // Un buen tipo de datos tiene la cantidad minima manteniendo la información que transmite
  /*
  Unit => tiene un valor (por qué se llama "unidad")
  Boolean => tiene dos valores
  Int => tiene 4.294.967.295 valores
  String => tiene efectivamente valores infinitos
   */
  /*
  Para encontrar la complejidad de un producto, multiplicamos la complejidad de cada parte.

  (Boolean, Boolean)tiene 4 valores ( 2*2)
  (Boolean, Boolean, Boolean)tiene 8 valores ( 2*2*2)
   */
  /*
  Para encontrar la complejidad de un coproducto, agregamos la complejidad de cada parte.

  (Boolean |: Boolean)tiene 4 valores ( 2+2)
  (Boolean |: Boolean |: Boolean)tiene 6 valores ( 2+2+2)
   */
  /*
  Para encontrar la complejidad de un ADT con un parámetro de tipo, multiplique cada parte por la complejidad del
  parámetro de tipo:

  Option[Boolean]tiene 3 valores Some[Boolean]y None( 2+1)
   */
  /*
  La complejidad de una función total es el número de funciones posibles que pueden satisfacer la firma de tipo:
  la salida a la potencia de la entrada.

  Unit => Boolean tiene complejidad 2
  Boolean => Boolean tiene complejidad 4
  Option[Boolean] => Option[Boolean] tiene complejidad 27
  Boolean => Int es un mero quintillón pasando un sextillón.
  Int => Boolean es tan grande que si a todas las implementaciones se les asignara un número único, cada una
  requeriría 4 gigabytes para representarlas.
   */
}
