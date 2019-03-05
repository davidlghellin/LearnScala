package es.david.listas

import es.david.entidades.Donnut

object CollectListas {
  // El metodo collect toma una funcion parcial y lo aplica a todos los elementos
  // para crear una nueva coleccion que satisfaga la funcion parcial
  // omitiendo aquellos que estan fuera del dominio de definicion
  // def collect[B](pf: PartialFunction[A, B]): Traversable[B]

  // Puede ayudarnos a obtener y puede reemplazar los maps y filters encadenados
  // http://ochafik.com/p_393

  def getPreciosDonnut(lista: List[Donnut]): List[Double] = {
    lista.collect { case donnut: Donnut => donnut.precio }
  }

  def getString(lista: List[Any]): List[String] = {
    lista.collect { case nombre: String => nombre }
  }
}