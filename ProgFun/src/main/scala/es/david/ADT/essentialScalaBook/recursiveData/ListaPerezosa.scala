package es.david.ADT.essentialScalaBook.recursiveData


final case class ListaPerezosa(head: Int, tail: () => ListaPerezosa)

object ListaPerezosaMain extends App {
  val unos: ListaPerezosa = ListaPerezosa(2, () => unos)
  println(unos)
}
