package es.david.Kleisli

import com.typesafe.scalalogging.{LazyLogging, StrictLogging}
//https://underscore.io/blog/posts/2012/07/02/kleisli-arrows.html

object KleisliApp extends App with StrictLogging {

  import scalaz._
  import Scalaz._

  // Some methods that take simple types
  // and return higher-kinded types
  def str(x: Int): Option[String] = Some(x.toString)

  def toInt(x: String): Option[Int] = Some(x.toInt)

  def double(x: Int): Option[Double] = Some(x * 2)

  //
  // Lets compose those functions Ye Olde Way
  def oldSchool(i: Int) =
    for (x <- str(i);
         y <- toInt(x);
         z <- double(y))
      yield z

  //
  // Kleisli!
  // andThenK
  val funky = Kleisli(str _) >=> Kleisli(toInt _) >=> Kleisli(double _)
  val funkyAndThen = Kleisli(str _) andThenK toInt andThenK double
  // composeK
  val funkyCompose = Kleisli(double _) <=< Kleisli(toInt _) <=< Kleisli(str _)
  val funkyCompose2 = Kleisli(double _) composeK Kleisli(toInt _) composeK Kleisli(str _)

  // usando un implicit
  //  https://stackoverflow.com/questions/39327726/using-scalaz-kleisli-without-explicit-wrapping-the-function-before/39330762#39330762
  implicit def toKleisliK[M[_], A, B]: (A => M[B]) => Kleisli[M, A, B] = f => {
    Kleisli[M, A, B](a => f(a))
  }

  // andThen
  val funkyImplAndThen = Kleisli(str _) >=> Kleisli(toInt _) >=> Kleisli(double _)
  val funkyImplAndThen3 = Kleisli(str _) >==> (toInt _) >==> (double _)
  val funkyImplAndThen2 = Kleisli(str _) andThen (toInt _) andThen (double _)
  // compose
  val funkyImplCompose = Kleisli(double _) <=< Kleisli(toInt _) <=< Kleisli(str _)
  val funkyImplCompose2 = Kleisli(double _) compose Kleisli(toInt _) compose Kleisli(str _)
  //
  logger.info(s"${oldSchool(1)}") // Some(2.0)
  logger.info(s"${funky(1)}") // Some(2.0)
  logger.info(s"${funkyCompose(1)}") // Some(2.0)
  logger.info(s"${funkyAndThen(1)}") // Some(2.0)
  logger.info(s"${funkyImplAndThen(1)}") // Some(2.0)
  logger.info(s"${funkyImplAndThen3(1)}") // Some(2.0)
  logger.info(s"${funkyImplAndThen2(1)}") // Some(2.0)
  logger.info(s"${funkyImplCompose(1)}") // Some(2.0)
  logger.info(s"${funkyImplCompose2(1)}") // Some(2.0)
  //
  //  // Lets use symbols!
  //    val reallyFunky = ☆(str) >=> (toInt _) >=> (double _)
}