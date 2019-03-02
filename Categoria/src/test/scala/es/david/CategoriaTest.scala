package es.david

import org.scalacheck.Prop.{forAll, _}
import org.scalacheck.Properties

object fixtures {
  val f = (x: Int) => x.toDouble
  val g = (y: Double) => y * y
  val h = (z: Double) => z.toString

  def square(a: Int) = a * a
}

object CategoriaTest extends Properties("Categoria") {

  import fixtures._

  property("Identidad") = forAll {
    (x: Int) => CategoriaSimpleHask.Id(x) == x
  }

  property("Id∘f = f") = forAll {
    (x: Int) => CategoriaSimpleHask.Id(square(x)) == square(x)
  }

  property("f∘Id = f") = forAll {
    (x: Int) => f(CategoriaSimpleHask.Id(x)) == f(x)
  }

  property("Associativity: h∘(g∘f) = (h∘g)∘f = h∘g∘f") = forAll {
    (x: Int) => CategoriaSimpleHask.compose(CategoriaSimpleHask.compose(f, g), h)(x) == CategoriaSimpleHask.compose(f, CategoriaSimpleHask.compose(g, h))(x)
  }

}
