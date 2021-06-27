package es.david.cap01

object TypeClass {

  case class Persona(nombre: String, edad: Int)

  // part1 definicion de la type class
  trait JSONSerlializer[T] {
    def toJson(value: T): String
  }

  // part2 creacion de implicitos , las instancias de las type class
  implicit object StringSerializer extends JSONSerlializer[String] {
    override def toJson(value: String): String = "\"" + value + "\""
  }

  implicit object IntSerializar extends JSONSerlializer[Int] {
    override def toJson(value: Int): String = value.toString
  }

  implicit object PersonaSerializer extends JSONSerlializer[Persona] {
    override def toJson(value: Persona): String =
      s"""
         |{ "nombre" : ${value.nombre}, "edad" : ${value.edad} }
         |""".stripMargin.trim
  }

  //part3 crear API
  def convertListTOJson[T](list: List[T])(implicit serializable: JSONSerlializer[T]): String =
    list.map(v => serializable.toJson(v)).mkString("[", ",", "]")

  //part4 extender los tipos vieaextension de metodos
  object JSONSintax {

    implicit class JSONSeriablzble[T](value: T)(implicit serializer: JSONSerlializer[T]) {
      def toJson: String = serializer.toJson(value)
    }

  }


  def main(args: Array[String]): Unit = {
    println(convertListTOJson(List(Persona("David", 33), Persona("Dana", 33))))

    val bob= Persona("Bob",23)
    import JSONSintax._
    println(bob.toJson)
    println(33.toJson)
    println("num".toJson)
  }
}
