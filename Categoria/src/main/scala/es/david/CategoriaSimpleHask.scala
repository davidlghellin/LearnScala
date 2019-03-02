package es.david


/*
https://elbauldelprogramador.com/teoria-categorias-scala-composicion/
https://bartoszmilewski.com/2014/11/04/category-the-essence-of-composition/
 */
object CategoriaSimpleHask {

  def Id[T](x: T): T = x

  def compose[A, B, C](f: A => B, g: B => C): A => C = f andThen g
}
