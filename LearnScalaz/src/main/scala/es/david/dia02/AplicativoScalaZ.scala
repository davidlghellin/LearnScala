package es.david.dia02

import scalaz.Scalaz._


object AplicativoScalaZ extends App {
 val a = 2.some
 val b = 2.some
 val apliCurry =  List(1, 2, 3, 4) map {(_: Int) * (_:Int)}.curried
 println(apliCurry map {_(9)})
 println(1.point[List])
 println(1.point[Option])
 println(1.point[Option] map {_ + 2})
 println()
 println(9.some <*> {(_: Int) + 3}.some)
 println(3.some <*> { 9.some <*> {(_: Int) + (_: Int)}.curried.some })

 println()
 println(^(3.some, 5.some) {_ + _})
 println((3.some |@| 5.some) (_ + _ ))
 println((3.some |@| 5.some|@| 51.some) (_ + _ + _))

 def suma(a: Int, b: Int) = a + b

 println((3.some |@| 5.some) (suma))
 println((a |@| b) {suma})
 println((a |@| b) ((x, y) => suma(x, y)))
 println(List(1, 2, 3) <*> List((_: Int) * 0, (_: Int) + 100, (x: Int) => x * x, (_: Int) * 1))
 println((List(1, 2, 3) <*> List((_: Int) * 0)))
 println()

 case class Nume(a: Int, b: Int)

 println((a |@| b) (Nume))
 println((a |@| None) (Nume))

 def getA: Option[Int] = Some(2)
 def getANone: Option[Int] = None
 println((a |@| getA) (Nume))
 println((a |@| getANone) (Nume))
 println((a |@| getANone) (Nume))

}
