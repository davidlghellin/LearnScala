package es.david.youtube.misil

sealed trait NukeException

case class SystemOfLine() extends NukeException

case class RotationsNeedOil() extends NukeException

case class MissedByMeters(meter: Int) extends NukeException

object EitherMisilApp extends App {
  def arm: Either[SystemOfLine, Nuke] = Right(Nuke())

  def aim: Either[RotationsNeedOil, Target] = Right(Target())

  def launch(target: Target, nuke: Nuke): Either[MissedByMeters, Impact] = Left(MissedByMeters(2))

  def attackMonadic: Either[NukeException, Impact] =
    for {
      nuke <- arm
      target <- aim
      impact <- launch(target, nuke)
    } yield impact

  println(attackMonadic)
}
