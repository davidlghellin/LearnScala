package es.david.basic

import zio._
import zio.console._

object LearnZioMyApp extends App {

  def run(args: List[String]) =
    myAppLogic.fold(_ => 1, _ => 0)

  val myAppLogic =
    for {
      _ <- putStrLn("Hello! What is your name?")
      name <- getStrLn
      _ <- putStrLn(s"Hello, ${name}, welcome to ZIO!")
    } yield ()
  // Para interactuar con la consola importaremos zio.console._
  // para escribir tenemos putStrl[Ln]
  // para leer getStrLn
}