package es.david.basic

import org.scalatest._
import zio.test.DefaultRunnableSpec
import zio.test.environment.TestConsole
import zio._
import zio.console._
import zio.test.Assertion._
import zio.test._
import zio.test.environment._
//
//class LearnZioMyAppSpec extends FlatSpec with Matchers {
//  "The Hello object" should "say hello" in {
//    "hello" shouldEqual "hello"
//  }
//
//  var outputs: Vector[String] = Vector.empty
//  object TestConsole extends Console {
//    def println(line: String): Unit = outputs = outputs :+ line
//    def readLine: String = "test"
//  }
//  outputs shouldBe
//    Vector("Good morning, what is your name?", "nice to meet you user-test")
//}
//object HelloWorldSpec2 extends DefaultRunnableSpec {
//  def spec = suite("es.david.basic.HelloWorldSpec2")(
//    testM("sayHello correctly displays output") {
//      for {
//        _      <- LearnZioMyApp.myAppLogic
//        nombre <- TestConsole.feedLines("david")
//        output <- TestConsole.output
//      } yield assert(output)(equalTo(Vector(s"Hello, ${nombre}, welcome to ZIO!")))
//    }
//  )
//
//  def aa =  suite("aaa")(
//    testM("aaaaaa"){
//
//    }
//  )
//}