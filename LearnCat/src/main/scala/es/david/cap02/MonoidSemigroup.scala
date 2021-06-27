package es.david.cap02

object MonoidSemigroup extends App {

  trait Monoid2[A] {
    def combine(x: A, y: A): A

    def empty: A
  }

  def associativeLaw[A](x: A, y: A, z: A)
                       (implicit m: Monoid2[A]): Boolean = {
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)
                    (implicit m: Monoid2[A]): Boolean = {
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }

  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid1[A] extends Semigroup[A] {
    def empty: A
  }

  // Ejercicios
  object Monoid2 {
    def apply[A](implicit monoid: Monoid2[A]) =
      monoid
  }

  implicit val booleanAndMonoid: Monoid2[Boolean] =
    new Monoid2[Boolean] {
      //        true  false
      // true   true  false
      //false   false false
      def combine(a: Boolean, b: Boolean) = a && b

      def empty = true
    }
  implicit val booleanOrMonoid: Monoid2[Boolean] =
    new Monoid2[Boolean] {
      //        true  false
      // true   true  true
      //false   true  false
      def combine(a: Boolean, b: Boolean) = a || b

      def empty = false
    }

}
