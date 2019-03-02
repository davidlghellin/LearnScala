package es.david.ADT.essentialScalaBook.recursiveData

import org.scalatest.{FlatSpec, FunSuite, Matchers}
import es.david.ADT.essentialScalaBook.recursiveData._

class PairTest extends FlatSpec with Matchers {
  "" should "" in {
    val example = Pair(1, Pair(2, Pair(3, End)))
    assert(Pair.sum(example) == 6)
    assert(Pair.sum(example.tail) == 5)
    assert(Pair.sum(End) == 0)
  }
}
