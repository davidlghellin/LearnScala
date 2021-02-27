package es.david.visual_scala.c

object ConstMain extends App {
  // def const[X, Y](x: X)(y: Y): X
  println(Function.const(3)(List("a","b")))
}
