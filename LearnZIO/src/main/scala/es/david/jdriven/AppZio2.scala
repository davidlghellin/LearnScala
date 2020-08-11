package es.david.jdriven

import es.david.jdriven.XModule.X
import zio._
import zio.console.Console

object AppZio2  /*extends App*/ {
////  def run(args: List[String]): ZIO[ZEnv, Nothing, Int] = program
////
////  val logic: ZIO[Console with XModule, Nothing, Int] = (for {
////    _   <- console.putStrLn(s"I'm running!")
////    x   <- XModule.factory.x
////    _   <- console.putStrLn(s"I've got an $x!")
////  } yield 0)
////    .catchAll(e => console.putStrLn(s"Application run failed $e").as(1))
////
////  private val program = logic
////    .provideSome[Console] { c =>
////      new Console with XModule.Live {
////        override val console = c.
////        override val xInstance: X                  = X()
////      }
////    }
}