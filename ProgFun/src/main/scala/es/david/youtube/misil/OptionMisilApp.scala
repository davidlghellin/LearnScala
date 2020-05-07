package es.david.youtube.misil

object OptionMisilApp extends App {
  def arm: Option[Nuke] = None

  def aim: Option[Target] = None

  def launch(target: Target, nuke: Nuke): Option[Impact] = Some(Impact())

  def attackMonadic: Option[Impact] =
    for {
      nuke <- arm
      target <- aim
      impact <- launch(target, nuke)
    } yield impact

  println(attackMonadic)
}
