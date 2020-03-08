package es.david.typeclasses

// https://typelevel.org/blog/2017/12/20/who-implements-typeclass.html


trait Adder[A] {
  def addImpl(a: A, b: A): A
}

object Adder {
  def add[A: Adder](x: A, y: A): A = implicitly[Adder[A]].addImpl(x, y)

  implicit def addInt = new Adder[Int] {
    override def addImpl(a: Int, b: Int): Int = a + b
  }

  implicit def addString = new Adder[String] {
    override def addImpl(a: String, b: String): String = s"$a$b"
  }

  implicit def addVects[A] = new Adder[Vector[A]] {
    override def addImpl(a: Vector[A], b: Vector[A]): Vector[A] = a ++ b
  }

  def addTree[A: Adder](x: A, y: A, z: A): A = {
    val m = implicitly[Adder[A]]
    m.addImpl(x, m.addImpl(y, z))
  }


  def zipAdd[A: Adder](l: List[A], r: List[A]): List[A] =
    l zip r map { case (x, y) => add(x, y) }

  //  implicit def addIntPairs = new Adder[(Int, Int)] {
  //    override def addImpl(a: (Int, Int), b: (Int, Int)): (Int, Int) = (a._1 + b._1, a._2 + b._2)
  //  }

  implicit def addPairs[A: Adder, B: Adder] = new Adder[(A, B)] {
    override def addImpl(a: (A, B), b: (A, B)): (A, B) = (add(a._1, b._1), add(a._2, b._2))
  }
}


object MainAdder extends App {

  import Adder._

  println(add(1, 2))
  println(addTree(1, 2, 4))
  println(add((2, 7), (3, 8)))
  println(zipAdd(List(1, 3), List(2, 4)))
  println(add(("c", "d"), ("e", "f")))
  println(add(("a", "b"), add(("c", "d"), ("e", "f"))))
  println(add((2, "x"), (3, "y")))
}
