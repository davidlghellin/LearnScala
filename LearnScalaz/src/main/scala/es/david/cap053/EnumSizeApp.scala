package es.david.cap053

import scalaz._


object EnumSizeApp extends App {

  import Size._

  val s: Size = SMALL
  val m: Size = MEDIUM
  val l: Size = LARGE

  println(s)
  implicit val sizeOrdering = Order[Size].toScalaOrdering
  println(List(m, l, s).sorted)
  println(implicitly[Enum[Size]].max)
  println(implicitly[Enum[Size]].min)

  val sizeEnum = implicitly[Enum[Size]]
  println(sizeEnum.max)

  println(Enum[Size].from(s).take(5).toList) // List(Size(0,SMALL), Size(1,MEDIUM), Size(2,LARGE), Size(0,SMALL), Size(1,MEDIUM))
  println(Enum[Size].fromToL(s, l))
}

case class Size(val size: Int, val name: String)

object Size {


  val SMALL = Size(0, "SMALL")
  val MEDIUM = Size(1, "MEDIUM")
  val LARGE = Size(2, "LARGE")

  implicit val SizeEnum: Enum[Size] with Show[Size] = new Enum[Size] with Show[Size] {
    def order(s1: Size, s2: Size): Ordering = (s1.size compare s2.size) match {
      case -1 => Ordering.LT
      case 0 => Ordering.EQ
      case 1 => Ordering.GT
    }

    def succ(s: Size): Size = s match {
      case SMALL => MEDIUM
      case MEDIUM => LARGE
      case LARGE => SMALL
      // tenemos que añadir este caso porque sino peta, revisar
      case _ => SMALL
    }

    def pred(s: Size): Size = s match {
      case SMALL => LARGE
      case MEDIUM => SMALL
      case LARGE => MEDIUM
      // tenemos que añadir este caso porque sino peta, revisar
      case _ => SMALL
    }

    def zero: Size = SMALL

    override def shows(s: Size) = s.name

    override def max = Some(LARGE)

    override def min = Some(SMALL)
  }
}
