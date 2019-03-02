package es.david.ADT.trees

import scala.collection.{immutable, mutable}

sealed trait Colour[+A]
case class R[A](a:A) extends Colour[A]
case class B[A](a:A) extends  Colour[A]

sealed trait Tree[+A]
case object E extends Tree[Nothing]
case class T[A](l: Tree[A], elem: Colour[A], r: Tree[A]) extends Tree[A] 