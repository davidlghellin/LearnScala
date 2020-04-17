package es.david.Kleisli

import com.typesafe.scalalogging.LazyLogging


//import com.typesafe.scalalogging.StrictLogging


object Ejemplo01Normal extends App with LazyLogging {

  val getNumberFromDB: () => Int = () => 2
  val getNumberFromDB2: Function0[Int] = new Function0[Int] {
    override def apply(): Int = 2
  }
  val proccessNumber: Int => Int = _ * 2
  val writeNumberIntoDB: Int => Boolean = _ => true
  //
  val combo1: Boolean = {
    val number = getNumberFromDB()
    val processed = proccessNumber(number)
    val result = writeNumberIntoDB(processed)
    result
  }
  //
  val combo2: () => Boolean = () => writeNumberIntoDB(proccessNumber(getNumberFromDB()))

  val combro2q: Int = getNumberFromDB2()
  logger.info(s"Resultado combo1: ${combo1}")
  logger.info(s"Resultado combo2: ${combo2()}")
}
