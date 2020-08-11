package es.david.bartosz.cap01

import org.scalacheck.Prop.{forAll, _}
import org.scalacheck.Properties

object Ejemplos01Test extends Properties("Cap01") {

  property("Identidad1") = forAll {
    (x: Int) => Ejemplos01.myId(x) == x
  }
  property("Identidad2") = forAll {
    (x: Int) => Ejemplos01.identidad(x) == x
  }
  property("Identidad3") = forAll {
    (x: Int) => Ejemplos01.myId(x) == Ejemplos01.identidad(x)
  }

  property("Identidad ∘ f") = forAll {
    (x: Int) => Ejemplos01.myId(Ejemplos01.m1(x)) == Ejemplos01.m1(x)
  }
  property("f ∘ Identidad") = forAll {
    (x: Int) => Ejemplos01.m1(x) == Ejemplos01.myId(Ejemplos01.m1(x))
  }
}
