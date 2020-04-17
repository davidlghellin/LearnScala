package es.david.Kleisli

import com.typesafe.scalalogging.StrictLogging

// https://github.com/hermannhueck/composing-functions/blob/master/slides/From-Function1apply-to-Kleisli.pdf

object Function1apply extends App with StrictLogging {
  val str2Int: String => Int = str => str.toInt
  val str2Int2: Function1[String, Int] = new Function[String, Int] {
    override def apply(str: String): Int = str.toInt
  }
  val str2Int3: Function1[String, Int] = (str: String) => str.toInt
  val mapped = List("1", "2", "3").map(str2Int)
  logger.info(s"${mapped}")
  val plus100: Int => Int = n => n + 100
  logger.info(s"${Option(5).map(plus100)}")
  // Error
  // logger.info(s"${str2Int  map(plus100)}")

  // Toda funcion es Funcion1
  logger.info(s"Funcion1")
  val sum3Int: (Int, Int, Int) => Int = _ + _ + _
  val sum3Int2: Function3[Int, Int, Int, Int] = _ + _ + _
  logger.info(s"${sum3Int(1, 2, 3)}")
  logger.info(s"${sum3Int2(1, 2, 3)}")

  // Hacemos el tupled
  logger.info(s"tupled")
  val sum3Tupled: ((Int, Int, Int)) => Int = sum3Int.tupled
  val sum3Tupled2: Function1[(Int, Int, Int), Int] = sum3Int2.tupled
  logger.info(s"${sum3Tupled(1, 2, 3)}")
  logger.info(s"${sum3Tupled2(1, 2, 3)}")
  // Deshacemos el tupled
  logger.info(s"unTupled")
  val sumUnTuped = Function.untupled(sum3Tupled)
  logger.info(s"${sumUnTuped(1, 2, 3)}")

  // Currificamos
  logger.info(s"Curry")
  val sum3Curried: Int => Int => Int => Int = sum3Int.curried
  val sum3Curried2: Function1[Int, Function1[Int, Function1[Int, Int]]] = sum3Curried
  val sumUnCurried: (Int, Int, Int) => Int = Function.uncurried(sum3Curried)
  logger.info(s"Parcial aplicacion de las funciones currificadas")
  val applied1st: Int => Int => Int = sum3Curried(1)
  val applied2nd: Int => Int = applied1st(2)
  val applied3rd: Int = applied2nd(3)
  val appliedAll: Int = sum3Curried(1)(2)(3)
  logger.info(s"${applied3rd}")
  logger.info(s"${appliedAll}")


  logger.info(s"Function1: apply, compose, andThen")
}
