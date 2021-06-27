package es.david.dia06

object WriterExample extends App {

  import scalaz._

  // DList has constant time append, which is nice for logging
  // for simplicity we will just use String for logs, but you could
  // get fancier and have a model with Error, Warn, Info, Debug, etc.
  type Logged[A] = Writer[DList[String], A]

  def log(msg: String): Logged[Unit] = WriterT.tell(DList(msg))

  val r = for {
    _ <- log("start")
    first = 1
    _ <- log(s"initialized first: $first")
    second = 2
    _ <- log(s"initialized second: $second")
    _ <- log("finish")
  } yield first + second

  val (logs, sum) = r.run
  logs.toList.foreach(println) // print the logs
  println()
  println(logs.toList)
  println()
  println(s"sum: $sum") // print the result

  // this app prints the following output:
  // start
  // initialized first: 1
  // initialized second: 2
  // finish
  // sum: 3
}
