package es.david.dia03

import scalaz._
import Scalaz._

object MonoideScalaZ extends App {
  /*
  Un monoide es cuando tiene una función binaria asociativa
  y un valor que actúa como una identidad con respecto a esa función.
   */
  println(List(1, 2, 3) mappend List(4, 5, 6))
  println(List(1, 2, 3) |+| List(4, 5, 6))
  println()
  println("one" mappend "two")
  //tenemos muchas tags
  // https://oss.sonatype.org/service/local/repositories/releases/archive/org/scalaz/scalaz_2.11/7.1.0/scalaz_2.11-7.1.0-javadoc.jar/!/index.html#scalaz.Tags$
  println(Tags.Multiplication(10) |+| Monoid[Int @@ Tags.Multiplication].zero)

  println(Tags.Disjunction(true) |+| Tags.Disjunction(false))
  println(Tags.Disjunction(true) |+| Monoid[Boolean @@ Tags.Disjunction].zero)

  // ordenar como monoide
  def lengthCompare(lhs: String, rhs: String): Ordering = {
    (lhs.length ?|? rhs.length) |+| (lhs ?|? rhs)
  }
  println(lengthCompare("zen", "ants"))
  println(lengthCompare("zen", "ant"))
}
