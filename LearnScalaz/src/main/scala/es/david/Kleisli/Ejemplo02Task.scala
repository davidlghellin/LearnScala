package es.david.Kleisli

//import com.typesafe.scalalogging.StrictLogging

import com.typesafe.scalalogging.{LazyLogging, StrictLogging}
import scalaz.Kleisli
import scalaz.concurrent.Task


object Ejemplo02Task extends App with StrictLogging {

  val getNumberFromDB: () => Task[Int] = () => {
    logger.info("Leemos num de bbdd")
    Task.now(2)
  }

  val proccessNumber: Int => Task[Int] = num => {
    logger.info("Procesamos num")
    Task.now(num * 2)
  }
  val writeNumberIntoDB: Int => Task[Boolean] = number => {
    logger.info(s"Escribiendo $number en bbdd")
    Task.now(true)
  }

  def flatMap() = {
    val combo: () => Task[Boolean] = () => {
      getNumberFromDB() flatMap { number =>
        proccessNumber(number) flatMap { processed =>
          writeNumberIntoDB(processed)
        }
      }
    }
    logger.info(s"El comboFlatMap: ${combo().unsafePerformSync}")
  }

  def forComprehension() = {
    val comboFor: () => Task[Boolean] = () => {
      for {
        number <- getNumberFromDB()
        processed <- proccessNumber(number)
        result <- writeNumberIntoDB(processed)
      } yield result
    }
    logger.info(s"El comboFlatMap: ${comboFor().unsafePerformSync}")
  }

  val identidadF: Int => Task[Int] = num => {
    logger.info("Leemos $num de bbdd")
    Task.now(num)
  }
  val indent: Int => Int = { num => num }

  def KleisliF = {

    // No he sabido como hacerlo de un funcion sin parametros
    val getNumberKleisli: Kleisli[Task, Int, Int] = Kleisli(identidadF)
    val proccessNumberKleisli: Kleisli[Task, Int, Int] = Kleisli.apply(proccessNumber)
    val writeNumberIntoDBKleisli: Kleisli[Task, Int, Boolean] = Kleisli.apply(writeNumberIntoDB)

    val comboKleisliNoVa: Kleisli[Task, Int, Boolean] = getNumberKleisli andThen
      proccessNumberKleisli andThen
      writeNumberIntoDBKleisli

    val comboKleisli: Int => Task[Boolean] = (getNumberKleisli andThen
      proccessNumberKleisli andThen
      writeNumberIntoDBKleisli).run

    logger.info(s"El comboKleisli: ${comboKleisli(2).unsafePerformSync}")
  }

  //  def KleisliFunNormal = {
  //
  //    val comboKleisli:Int => Task[Boolean] =  (Kleisli(indent _) andThenK
  //      proccessNumber andThenK
  //      writeNumberIntoDB
  //    ).run
  //
  //    println(s"El comboKleisli: ${comboKleisli(2).unsafePerformSync}")
  //  }


  flatMap
  println()
  forComprehension
  println()
  KleisliF
}
