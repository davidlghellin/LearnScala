package es.david.dia0

trait PlusIntf[A] {
    def plus(a2: A): A
  }

object PlusIntf{
    def plusBySubtype[A <: PlusIntf[A]](a1: A, a2: A): A = a1.plus(a2)
}