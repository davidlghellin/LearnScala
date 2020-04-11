package es.david.cap041

object Data410 extends App{

  /*
   * La construccion fundamental de los data types son:
   * final case class conocido como productos
   * sealed abstract class conocido como coproductos
   * cse object e Int Double...
   */

  /*
   * Tenemos dos posibilidades el producto y copordoucto
   * Producto: JKL = j AND k AND l
   * Coproducto:IOP= i XOR o XOR p
   */
  // valores
  case object A
  type B = String
  type C = Int

  // Producto
  final case class ABC(a: A.type, b: B, c: C)

  // Copodructo
  sealed abstract class XYZ
  case object X extends XYZ
  case object Y extends XYZ
  final case class Z(b: B) extends XYZ

  val abc: ABC = ABC(A, "b", 0)
  println(abc)

  val z: Z = Z("Dato")
  println(z)
}
