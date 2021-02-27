package es.david.intro

object EjemploTiposProductos extends App {

  // tipos genericos de datos
  sealed trait Shape

  final case class Rectangle(width: Double, height: Double) extends Shape

  final case class Circle(radius: Double) extends Shape

  val rect: Shape = Rectangle(3.0, 4.0)
  val circ: Shape = Circle(1.0)

  def area(shape: Shape): Double =
    shape match {
      case Rectangle(w, h) => w * h
      case Circle(r) => math.Pi * r * r
    }

  println(area(rect))
  println(area(circ))

  // Codificaciones alternativas
  type Rectangle2 = (Double, Double)
  type Circle2 = Double
  type Shape2 = Either[Rectangle2, Circle2]

  val rect2: Shape2 = Left((3.0, 4.0))
  val circ2: Shape2 = Right(1.0)

  def area2(shape: Shape2): Double =
    shape match {
      case Left((w, h)) => w * h
      case Right(r) => math.Pi * r * r
    }

  println(area2(rect2))
  // esta codificacion es mas generica
  // normalmente usaremos la 1
  // pero por ejemplo si estamos serializando nos da igual un rectangulo o una tupla de dobles


  import shapeless.{::, HNil}

  val product: String :: Int :: Boolean :: HNil =
    "Sunday" :: 1 :: false :: HNil

  val first = product.head
  // first: String = Sunday

  val second = product.tail.head
  // second: Int = 1

  val rest = product.tail.tail
  // rest: Boolean :: shapeless.HNil = false :: HNil

  val newProduct: Long :: String :: Int :: Boolean :: HNil = 42L :: product
  // newProduct: Long :: String :: Int :: Boolean :: HNil = 42 :: Sunday :: 1 :: false :: HNil

  ////
  // Genericos
  import shapeless.Generic

  case class IceCream(name: String, numCherries: Int, inCone: Boolean)

  val iceCreamGen = Generic[IceCream]
  // iceCreamGen: shapeless.Generic[IceCream]{type Repr = String :: Int :: Boolean :: shapeless.HNil} = anon$macro$4$1@6b9323fe
  println(iceCreamGen)
  // Generic tiene dos metodos 'to' 'from'
  val iceCream = IceCream("Sundae", 1, false)
  // iceCream: IceCream = IceCream(Sundae,1,false)

  val repr = iceCreamGen.to(iceCream)
  // repr: iceCreamGen.Repr = Sundae :: 1 :: false :: HNil

  val iceCream2 = iceCreamGen.from(repr)
  // iceCream2: IceCream = IceCream(Sundae,1,false)

  // Si dos ADT tienen la misma representacion podemos
  case class Employee(name: String, number: Int, manager: Boolean)

  // Create an employee from an ice cream:
  val employee = Generic[Employee].from(Generic[IceCream].to(iceCream))
  // employee: Employee = Employee(Sundae,1,false)
}
