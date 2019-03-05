package es.david.listas

import es.david.entidades.Donnut

object AggregateListas {
  /*
      En estos metodos vamos a ver como podemos usar la función aggregate, principalmente son dos metodos
      Estos metodos son de agregacion y luego la combinacion de todas
  */

  // def aggregate[B](z: ⇒ B)(seqop: (B, A) ⇒ B, combop: (B, B) ⇒ B): B

  def aggregateSumaInt(lista: List[Int]): Int = {
    // recibe dos parametros
    // en el primero tenemos el valor de inicializacion
    // en el segundo tenemos, otros dos
    //   funcion con la que combinaremos los datos por primera vez
    //   segunda funcion que para combinar y dar el resultado final
    lista.aggregate(0)(
      { (acc, valor) => acc + valor },
      { (a, b) => a + b }
    )
  }

  def aggregateSumaChar(lista: List[Char]): Int = {
    // en este ejemplo vamos a ver como de una lista de caracteres lo pasamos a la suma de los enteros
    lista.aggregate(0)(
      { (acc, valor) => acc + valor.toInt },
      { (a, b) => a + b }
    )
  }

  def sumaLengthCadenas(lista: List[String]): Int = {
    lista.aggregate(0)(
      (acc, valor) => acc + valor.length,
      (a, b) => a + b
    )
  }


  def calculaPedidoDonnuts(listaDonnut: List[Donnut]): Double = {
    val totalCostAccumulator: (Double, Double, Int) => Double =
      (accumulator, price, quantity) => accumulator + (price * quantity)

    listaDonnut.aggregate(0.0)(
      (acc, donut) => totalCostAccumulator(acc, donut.precio, donut.cantidad),
      (a, b) => a + b
    )
  }
}