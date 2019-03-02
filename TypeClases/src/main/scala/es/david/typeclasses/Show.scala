package es.david.typeclasses

//https://blog.scalac.io/2017/04/19/typeclasses-in-scala.html
trait Show[A] {
  def show(a: A): String
}
//
//object Show {
//  val intCanShow: Show[Int] =
//    new Show[Int] {
//      def show(int: Int): String = s" int $int"
//    }
//
//  def main(args: Array[String]): Unit = {
//    println(intCanShow.show(29))
//  }
//}

