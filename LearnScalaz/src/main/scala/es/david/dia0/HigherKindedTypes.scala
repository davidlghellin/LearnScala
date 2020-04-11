package es.david.dia0

trait Foo[C[_]] {
  def create(i: Int): C[Int]
}

// Constructor de tipos
// List es un constructor de tipos ya que List[Int]
object FooList extends Foo[List] {
  override def create(i: Int): List[Int] = List(i)
}

// Podemos definir Foo de cualquier cosa con un agujero de parametro
// Desafortunadamente es algo torpe y tenemos que escfribir alias
class TypeAlias {
  type EitherString[T] = Either[String, T]

  // Los alias no crean nuevos tipos y solamente sustituyen la parte derecha donde encuentra la parte izquierda
  object FooEitherStrin extends Foo[EitherString] {
    override def create(i: Int): EitherString[Int] = Right(i)
    def create2(i: Int): Either[String, Int] = Right(i)
  }

}
