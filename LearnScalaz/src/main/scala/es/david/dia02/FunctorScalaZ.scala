package es.david.dia02

import scalaz.Functor
import scalaz.std.list._
import scalaz.std.option._

import scala.language.higherKinds

object FunctorScalaZ extends App {
  val list: List[Int] = Functor[List].map(List(1, 2, 3))(_ * 2)
  val option: Option[String] = Functor[Option].map(Some(123))(_.toString)
  println("List(1, 2, 3), [_ * 2] =>" + list)
  println("Some(123), _.toString =>" + option)

  def par(x:Int)={
    x% 2 match {
      case 0 =>Some(x)
      case _=> None
    }
  }
  println(par(2))
  println(par(21))

  println("-----")
  val lift: List[Int] => List[Int] = Functor[List].lift {(_: Int) * 2}
  val liftOption: Option[Int] => Option[Int] = Functor[Option].lift {(_: Int) * 2}
  println(lift)
  println(lift(List(3,4)))
  println(liftOption(Some(2)))
  println(liftOption(None))
  // compose functor
  def cuadrado: Int => Int = x => x * x

  val FO: Functor[Option] = Functor[Option]
  val FL: Functor[List] = Functor[List]
  val FL_FO = FL compose FO

  val listaOptions = List(Some(2), Some(3), Some(8), None)
  val resCompose: List[Option[Int]] = FL_FO.map(listaOptions)(cuadrado)
  val res:        List[Option[Int]] = listaOptions.map(x=> x.map(y=>cuadrado(y)))
  println(resCompose)
  println(res)

  case class Clase1(num:Int)
  object Clase1{
    def suma (c:Clase1)(sumNum:Int)= c.copy(num = c.num + sumNum)
  }
  println(FO.map(Some(Clase1(8)))(Clase1.suma(_)(2)))
}
