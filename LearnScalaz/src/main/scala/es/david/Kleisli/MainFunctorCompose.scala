package es.david.Kleisli

import com.typesafe.scalalogging.StrictLogging

object MainFunctorCompose extends App with StrictLogging {
  val l1: List[Option[Int]] = List(Some(2), None, Some(3))
  logger.info(s"${l1.map(_.map(_ + 1))}")

  import scalaz._
  import Scalaz._

  // https://www.youtube.com/watch?v=4ODUEbowkBM
  val l2: List[Option[Int]] = Functor[List].compose[Option].map(l1)(_ + 1)
  logger.info(s"${l2}")

  val lstDeLst: List[List[Option[Int]]] = List(List(Some(2), None, Some(3)), List(Some(2), None, Some(3)))
  val resLstLst = Functor[List].compose[List].compose[Option].map(lstDeLst)(_ + 1)
  logger.info(s"${resLstLst}")

  // https://stackoverflow.com/questions/31284131/how-to-simplify-nested-map-calls
  def map2[F[_], G[_], A, B](fg: F[G[A]])(f: A => B)
                            (implicit F0: Functor[F], G0: Functor[G]): F[G[B]] =
    F0.map(fg)(g => G0.map(g)(f))

  def map3[F[_], G[_], H[_], A, B](fg: F[G[H[A]]])(f: A => B)
                                  (implicit F0: Functor[F], G0: Functor[G], H0: Functor[H]): F[G[H[B]]] =
    F0.map(fg)(g => G0.map(g)(h => H0.map(h)(f)))
}
