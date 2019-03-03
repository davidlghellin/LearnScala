package es.david.listas

import org.scalatest.{FlatSpec, Matchers}
import es.david.listas._

class AggregateListasTest extends FlatSpec with Matchers {
    "Aggregate" should "int" in {
        val listaEnteros: List[Int] = List(1,2,3,4,5,6,7,8,9,0)
        assert(AggregateListas.aggregateSumaInt(listaEnteros) === 45)
    }

    it should "caracteres suma" in {
        val listaChar: List[Char] = List('a', 'b', 'c')
        assert (AggregateListas.aggregateSumaChar(listaChar) === 294)
    }

    "Suma de longitudes de cadena" should "valor correcto" in {
        val listaCadenas:List[String] = List("esto suma","11")
        assert(AggregateListas.sumaLengthCadenas(listaCadenas) === 11)
    }

    "Suma donnut" should "la suma correcta" in {
        val donut1:Donnut = Donnut("Plain Donut", 1.50, 10)
        val donut2:Donnut = Donnut("Strawberry Donut", 2.0, 10)
        val listaDonnuts :List[Donnut] = List(donut1,donut2)
        
        assert(AggregateListas.calculaPedidoDonnuts(listaDonnuts) === 35.0)
    }
}