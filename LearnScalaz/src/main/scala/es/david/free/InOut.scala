package es.david.free

import scalaz._
import scalaz.concurrent.Task

// https://github.com/EncodePanda/make-your-programs-free/blob/master/src/main/scala/free/InOut.scala
sealed trait InOut[A]

case class PrintLine(line: String) extends InOut[Unit]

case object GetLine extends InOut[String]

object InOut {
  def printLine(line: String): Free[InOut, Unit] = Free.liftF(PrintLine(line))

  def getLine(): Free[InOut, String] = Free.liftF(GetLine)
}

object RunInOut extends App {

  import InOut._

  val program: Free[InOut, Unit] = for {
    _ <- printLine("Como te llamas??")
    name <- getLine()
    _ <- printLine(s"Encantado de conocerte $name")
  } yield ()

  // despues de haber creado el inerprete
  val task: Task[Unit] = program.foldMap(ConsoleInterpreter)
  task.unsafePerformSync
}

// F~>G === F[A] => G[A]
object ConsoleInterpreter extends (InOut ~> Task) {
  override def apply[A](inout: InOut[A]): Task[A] = inout match {
    case PrintLine(line) => Task.delay {
      println(line)
    }
    case GetLine => Task.delay {
      scala.io.StdIn.readLine()
    }
  }
}
