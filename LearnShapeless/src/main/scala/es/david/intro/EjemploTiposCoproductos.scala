package es.david.intro

object EjemploTiposCoproductos extends App {

  import shapeless.{:+:, CNil, Inl, Inr}

  case class Red()

  case class Amber()

  case class Green()

  // Luz = roja o ambar o verde
  type Light = Red :+: Amber :+: Green :+: CNil
  val red: Light = Inl(Red())
  // red: Light = Inl(Red())

  val green: Light = Inr(Inr(Inl(Green())))
  // green: Light = Inr(Inr(Inl(Green())))
  println(red)
  println(green)

  //

  import shapeless.Generic

  sealed trait Shape
  final case class Rectangle(width: Double, height: Double) extends Shape
  final case class Circle(radius: Double) extends Shape

  val gen = Generic[Shape]
  // gen: shapeless.Generic[Shape]{type Repr = Rectangle :+: Circle :+: shapeless.CNil} = anon$macro$1$1@1a28fc61
  println(gen)

  // El Repr de la Genericpara Shapees una Coproductde los subtipos del rasgo sellado: Rectangle :+: Circle :+: CNil.
  // Podemos usar los métodos toy fromdel genérico para mapear hacia adelante y hacia atrás entre Shapey gen.Repr:
  gen.to(Rectangle(3.0, 4.0))
  // res3: gen.Repr = Inl(Rectangle(3.0,4.0))
  gen.to(Circle(1.0))
  // res4: gen.Repr = Inr(Inl(Circle(1.0)))
}
