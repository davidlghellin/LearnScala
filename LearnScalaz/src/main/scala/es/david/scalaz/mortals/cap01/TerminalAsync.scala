package es.david.scalaz.mortals.cap01

import scala.concurrent.Future

trait TerminalAsync {
  def read(): Future[String]
  def write(t: String): Future[Unit]
}
