package es.david.derivacionClasesTipos

import es.david.derivacionClasesTipos.ClasesTipos01.{CsvEncoder, IceCream}


object IdeomaticTypesResolution extends App {

  // lo comun es hacer el comapnion boject
  object CsvEncoder {
    // "Summoner" method
    // El metodo apply sabe materializar
    def apply[A](implicit enc: CsvEncoder[A]): CsvEncoder[A] =
      enc

    // "Constructor" method
    def instance[A](func: A => List[String]): CsvEncoder[A] = new CsvEncoder[A] {
      def encode(value: A): List[String] =
        func(value)
    }

    // Globally visible type class instances
  }

  import es.david.derivacionClasesTipos.ClasesTipos01.Helado.iceCreamEncoder

  println(CsvEncoder[IceCream])
  println(implicitly[CsvEncoder[IceCream]])

  // si no detecta bien el encoder implicito podremos usar the

  import shapeless._

  println(the[CsvEncoder[IceCream]])

  implicit val booleanEncoder: CsvEncoder[Boolean] =
  new CsvEncoder[Boolean] {
    def encode(b: Boolean): List[String] =
    if (b) List("yes") else List("no")
  }
  // forma corta
  // trait CsvEncoder[A] {
  //   def encode(value: A): List[String]
  // }
  //import es.david.derivacionClasesTipos.IdeomaticTypesResolution.CsvEncoder._
  //implicit val booleanEncoder: CsvEncoder[Boolean] = instance(b => if(b) List("yes") else List("no"))
}
