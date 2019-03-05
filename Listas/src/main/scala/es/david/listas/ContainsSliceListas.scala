package es.david.listas

object ContainsSliceListas {
  def getContains[T](lista: List[T], elem: Int): Boolean = {
    lista.contains(elem)
  }

  def getContainsSlice[T](lista: List[T], listaBuscar: List[T]): Boolean = {
    lista.containsSlice(listaBuscar)
  }
}
