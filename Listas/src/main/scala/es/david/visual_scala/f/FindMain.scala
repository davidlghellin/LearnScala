package es.david.visual_scala.f

object FindMain extends App {
  // def find(p: (A) => Boolean): Option[A]
  // find devuelve el primer elemento que satisface el predicado p, envuelto con Some.
  // Si ningún elemento de esta colección satisface p entonces esta función devuelve None.
  case class User(nombre: String, edad: Int)

  val usuDavid = User("David", 22)
  val usuCess = User("Cess", 33)
  val usuPed = User("Pep", 38)
  val users = usuDavid :: usuCess :: usuPed :: Nil

  val res = users.find(_.edad>30)
  println(res)
}
