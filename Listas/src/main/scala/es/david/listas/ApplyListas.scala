package es.david.listas

object ApplyListas {
  // este metodo toma el valor del indice
  def getApply(lista: List[Int], n: Int): Int = {
    lista.apply(n)
  }
}