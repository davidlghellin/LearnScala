package es.david.meetup

import java.nio.file.{Files, Path}
import java.time.LocalTime

import es.david.basic.LearnZioMyApp.myAppLogic
import zio.{IO, Task, UIO, ZIO}

object AAAA extends App {
//    val path = ???
//    val newFile: Task[Path] = ZIO.effect(Files.createFile(path))
//
//    // no puede fallar y no van a devolver efectos de lado
//    val localtime: UIO[LocalTime] = ZIO.effectTotal(java.time.LocalTime.now)
//    val number: UIO[Int] = ZIO.succeed(42)
//
//    case class Error(msg: String)
//
//    val failure: IO[Error, Nothing] = ZIO.fail(Error("RIP"))

  val fileCreator: ZIO[Any, Throwable, Path] = for {
    time <- ZIO.effectTotal(java.time.LocalTime.now)
    filename = s"myfile-$time.txt"
    pathF = Path.of("home", "user", filename)
    newFile <- ZIO.effect(Files.createFile(pathF))
  } yield pathF


  def run(args: List[String]) =
    fileCreator.fold(_ => 1, _ => 0)
}
