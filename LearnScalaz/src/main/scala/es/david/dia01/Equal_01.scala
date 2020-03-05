package es.david.dia01

import scalaz._, Scalaz._

object Equal_01 extends App {
  println(1 === 1) // true
  //  println(1 === "foo")
  //  println(1 == "foo")
  println(1.some =/= 2.some) //true
  //  println(1 assert_=== 2) //java.lang.RuntimeException: 1 ≠ 2
}
