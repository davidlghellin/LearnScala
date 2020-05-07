package es.david.youtube.misil

import cats._
import cats.implicits._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext.Implicits.global

// https://github.com/47degrees/functional-error-handling/blob/master/src/main/scala/feh/examples/monaderror.sc
object GenericMonadMisilApp extends App {
  type NukeMonadError[M[_]] = MonadError[M, NukeException]

  //  def arm[M[_]]: M[Nuke] = ???
  //  def aim[M[_]]: M[Target] = ???
  //  def launch[M[_]](target: Target, nuke: Nuke): M[Impact] = ???

  def arm[M[_] : NukeMonadError]: M[Nuke] =
    Nuke().pure[M] // lo va a activar, Some, Succes

  def aim[M[_] : NukeMonadError]: M[Target] =
    Target().pure[M]

  def launch[M[_] : NukeMonadError](target: Target, nuke: Nuke): M[Impact] =
    (MissedByMeters(5000): NukeException).raiseError[M, Impact]


  // vamos a definir atacar que antes era el for-comprehension
  def attack[M[_] : NukeMonadError]: M[Impact] =
  //aim arm son paralilzables
  // |@| operaciones cartersianas => paralelizables
  // con el resultado creamos un el mismo formato
    (aim[M] |@| arm[M]).tupled.flatMap((launch[M] _).tupled)

  attack[Either[NukeException, ?]]

  ////////////////////
  // vamos a expresar uno especifico
  ////////////////////
  type Result[A] = Future[Either[NukeException, A]]

  implicit val resultMonadError: MonadError[Result, NukeException] =
    new MonadError[Result, NukeException] {
      override def pure[A](x: A): Result[A] = Future.successful(Right(x))

      override def raiseError[A](e: NukeException): Result[A] = Future.successful(Left(e))

      override def handleErrorWith[A](fa: Result[A])(f: (NukeException) => Result[A]): Result[A] =
        fa flatMap {
          case Left(nukeException) => f(nukeException)
          case Right(value) => pure(value)
        }

      override def flatMap[A, B](fa: Result[A])(f: (A) => Result[B]): Result[B] =
        fa flatMap {
          case Left(nukeException) => raiseError(nukeException)
          case Right(value) => f(value)
        }

      override def tailRecM[A, B](a: A)(f: (A) => Result[Either[A, B]]): Result[B] = {
        f(a).flatMap {
          case Left(ex) => raiseError(ex)
          case Right(c) => c match {
            case Left(cont) => tailRecM(cont)(f)
            case Right(a) => pure(a)
          }
        }
      }

      override def ap[A, B](ff: Result[(A) => B])(fa: Result[A]): Result[B] =
        fa.zip(ff).map { case (a, f) =>
          for {
            fr <- f
            ar <- a
          } yield fr(ar)
        }

    }

  val parallelizableAttack = attack[Result]
  val x = Await.result(parallelizableAttack, 10.seconds)
  println(x)
}
