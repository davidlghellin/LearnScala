package es.david.bartosz.cap02

import es.david.bartosz.cap02.Ejercicios021.Memoize1

import scala.util.Random

// TODO
object Ejercicios023 extends App {
  val seeds: Random = new scala.util.Random(1000)
  println(seeds.nextInt())


  val r = Memoize1((x: Int) => seeds)
  println(r)
}
