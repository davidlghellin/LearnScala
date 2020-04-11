package es.david.cap052

import java.time.LocalDate

import scalaz.Scalaz._
import scalaz._

sealed abstract class Currency
case object EUR extends Currency
case object USD extends Currency

final case class TradeTemplate(
                                payments: List[java.time.LocalDate],
                                ccy: Option[Currency],
                                otc: Option[Boolean]
                              )

object TradeTemplate {

  implicit val monoid: Monoid[TradeTemplate] = Monoid.instance(
    (a, b) => TradeTemplate(a.payments |+| b.payments, a.ccy |+| b.ccy, a.otc |+| b.otc),
    TradeTemplate(Nil, None, None)
  )

  // no se deberia de poner esto aquí
  // para no romper la coherencia de clase
  implicit def lastWins[A]: Monoid[Option[A]] = Monoid.instance(
    {
      case (None, None) => None
      case (only, None) => only
      case (None, only) => only
      case (_, winner) => winner
    },
    None
  )

  // si quiseramos que la lista de fechas fuera otro comportamiento
  // solamente tendríamos que implementar
  // Monoid[List[LocalDate]]
  implicit val monoidListDate: Monoid[List[LocalDate]] = Monoid.instance(
    (a, b) => a ::: b,
    List()
  )
}

object Main052 extends App {
  val zero = Monoid[TradeTemplate].zero

  val templates = List(
    TradeTemplate(Nil, None, None),
    TradeTemplate(Nil, Some(EUR), None),
    TradeTemplate(List(LocalDate.of(2017, 8, 5)), Some(USD), None),
    TradeTemplate(List(LocalDate.of(2017, 9, 5)), Some(EUR), Some(true)),
    TradeTemplate(Nil, None, Some(false))
  )
  println(templates.foldLeft(zero)(_ |+| _))
  println(templates.toIList.fold)
}