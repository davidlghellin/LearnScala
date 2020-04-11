package es.david.scalaz.mortals.cap01

import scala.concurrent.Future

trait TerminalSync {
  def read(): String
  def write(t: String): Unit
}
