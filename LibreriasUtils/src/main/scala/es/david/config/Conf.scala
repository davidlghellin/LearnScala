package es.david.config

import org.rogach.scallop._

// https://github.com/scallop/scallop
class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
  val apples = opt[Int](required = true)
  val bananas = opt[Int]()
  val name = trailArg[String]()
  verify()
}

object Main {
  def main(args: Array[String]) {
    //--apples 4 --bananas 10 strangeTree
    //-a 4 --bananas 10 strangeTree
    val conf = new Conf(args) // Note: This line also works for "object es.david.config.Main extends App"
    println(s"apples are:  ${conf.apples()}")
    println(s"Name prog :  ${conf.name()}")
  }
}