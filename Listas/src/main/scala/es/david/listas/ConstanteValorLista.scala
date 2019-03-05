package es.david.listas

object ConstanteValorLista {
  // siempre da el mismo valor a todos los elementos
  // def Function.const[T, U](x: T)(y: U): T
  def getConstante[T](lista: List[T], valor: T): List[T] = {
    lista.map(_ => valor) // alternativa a la funcion

    lista.map(Function.const(valor))
  }
}
