package es.david.basic

import zio.console.Console
import zio.{IO, ZIO, console}

// https://www.youtube.com/watch?v=5s0GOA3WQnY
object IOTypes extends App {
  val error = throw new Exception("error")
  val errorIO: IO[Exception, Nothing] = IO.fail(new Exception("error"))

  val number: Int = 1
  val numberIO: IO[Nothing, Int] = IO.succeed(1)

  val print = println("hola")
  val toInt: IO[Nothing, Unit] =
    IO.effectTotal(println("hola"))
  val parseInt: IO[Throwable, Int] =
    IO.effect("hola".toInt)

  //contextual
  val decirHola: ZIO[Console, Nothing, Unit] =
    console.putStrLn("hola")

  // si queremos recurperarnos de un efecto
  val parseIntIO: IO[Throwable, Int] =
    IO.effect("hola".toInt).catchSome {
      case e: NumberFormatException => IO.succeed(1)
    }
  // si queremos recuperarnos de tods tipo de errores
  val parseIntIOAll: IO[String, Int] =
    IO.effect("hola".toInt).catchAll(e => IO.fail(e.getMessage))
  val zeither: ZIO[Any, Nothing, Either[String, Int]] =
    IO.fail("uh oh").either

}
