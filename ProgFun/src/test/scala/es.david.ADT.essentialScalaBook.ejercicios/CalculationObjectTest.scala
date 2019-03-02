package es.david.ADT.essentialScalaBook.ejercicios


import org.scalatest.{FlatSpec, Matchers}
import es.david.ADT.essentialScalaBook.ejercicios._


class CalculationObjectTest extends FlatSpec with Matchers {

  "Suma resta" should "Correcta" in {
    assert(Calculator.+(Success(1), 1) == Success(2))
    assert(Calculator.-(Success(1), 1) == Success(0))
    assert(Calculator.+(Failure("Badness"), 1) == Failure("Badness"))
  }
  "division" should "Correcta" in {
    assert(Calculator./(Success(4), 2) == Success(2))
    assert(Calculator./(Success(4), 0) == Failure("Division by zero"))
    assert(Calculator./(Failure("Badness"), 0) == Failure("Badness"))
  }

}