package es.david.jdriven

import zio.console.Console
import zio.{ZIO, _}


// https://blog.jdriven.com/2019/10/functional-dependency-injection-in-scala-using-zio-environments/
object AppZio {

}
//object AppZio extends App {
//  //override def run(args: List[String]): ZIO[Console, Nothing, Int] = program
//
//  val logic: ZIO[Console, Nothing, Int] = (for {
//    _ <- console.putStrLn(s"I'm running!")
//  } yield 0)
//  // .catchAll(e => console.putStrLn(s"Application run failed $e").as(1))
//
//  private val program = logic
//}