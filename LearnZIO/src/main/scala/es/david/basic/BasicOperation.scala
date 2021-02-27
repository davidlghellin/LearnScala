package es.david.basic

import zio._
import zio.console._

object BasicOperation extends App {

  val succeded: UIO[Int] = IO.succeed(21).map(_ * 2)

  val failed: IO[Exception, Unit] =
    IO.fail("No no!").mapError(msg => new Exception(msg))

  val sequenced =
    getStrLn.flatMap(input => putStrLn(s"You entered: $input"))

  val zipped: UIO[(String, Int)] =
    ZIO.succeed("4").zip(ZIO.succeed(2))
  val zipRight1 =
    putStrLn("What is your name?").zipRight(getStrLn)

  val zipRight2 =
    putStrLn("What is your name?") *>
      getStrLn

  val program =
    for {
      a <- succeded
      _ <- putStrLn(s"valor ${a}")
      _ <- putStrLn(s"err $failed")
      _ <- putStrLn("Escriba lo que quiera")
      res <- sequenced
      _ <- putStrLn(s"sec $res")
      _ <- putStrLn("Hello! What is your name?")
      name <- getStrLn
      _ <- putStrLn(s"Hello, ${name}, welcome to ZIO!")
      _ <- putStrLn(s"${zipped}")
      _ <- putStrLn(s"${zipRight1}")
      _ <- putStrLn(s"${zipRight2}")
    } yield ()

  /*
   * El programa ejecutable
   */
  def run(args: List[String]) = ???
 //   program.fold(_ => 1, _ => 0)

  ////
  val parseInt: IO[String, Int] =
    IO.effect("3".toInt).catchAll(e => IO.fail(e.getMessage))

  val parseIntFail: IO[String, Int] =
    IO.effect("Hello".toInt).catchAll(e => IO.fail(e.getMessage))
}
