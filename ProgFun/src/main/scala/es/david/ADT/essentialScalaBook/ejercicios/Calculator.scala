package es.david.ADT.essentialScalaBook.ejercicios


sealed trait CalculationExe
final case class Success(result: Int) extends CalculationExe
final case class Failure(reason: String) extends CalculationExe

object Calculator {
  def +(cal: CalculationExe, n: Int): CalculationExe =
    cal match {
      case Success(result) => Success(result + n)
      case Failure(reason) => Failure(reason)
    }

  def -(cal: CalculationExe, n: Int): CalculationExe =
    cal match {
      case Success(result) => Success(result - n)
      case Failure(reason) => Failure(reason)
    }

  def /(cal: CalculationExe, n: Int): CalculationExe =
    cal match {
      case Success(result) => {
        n match {
          case 0 => Failure("Division by zero")
          case _ => Success(result / n)
        }
      }
      case Failure(reason) => Failure(reason)
    }
}
