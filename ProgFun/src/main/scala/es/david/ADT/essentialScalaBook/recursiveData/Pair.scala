package es.david.ADT.essentialScalaBook.recursiveData

final case class Broken(broken: Broken)


sealed trait IntList
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

object Pair {
  def sum(list: IntList): Int = {
    list match {
      case End => 0
      case Pair(head, tail) => head + sum(tail)
    }
  }
}


object Principal extends App {
  val pair = Pair(1, Pair(2, Pair(3, End)))
  println(pair)
}

sealed trait RecursiveExample
final case class RecursiveCase(recursion: RecursiveExample) extends RecursiveExample
case object BaseCase extends RecursiveExample
