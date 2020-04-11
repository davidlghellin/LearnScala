package es.david.typeclasses

object PrintableEjemplo {

  //https://github.com/alvinj/FPTypeClasses/blob/master/src/main/scala/typeclasses/v1_humanlike/BehavesLikeHuman.scala
  trait Printable[T] {
    //definifos los metodos usando tipos genericos
    def format(value: T): String
  }

  object PrintableInstance {
    // hacemos para cada tipo de clase lo que va a hacer
    // lo definimos como implicitos para poder pasarlo
    implicit val intInstance = new Printable[Int] {
      override def format(value: Int): String = value.toString
    }

    implicit val strInstance = new Printable[String] {
      override def format(value: String): String = s" -- $value --"
    }
  }

  object PrintableFace {
    def print[T](valor: T)(implicit printable: Printable[T]) = println(printable.format(valor))

    // otra forma de hacer lo mismo q print
    def printImplicitly[T: Printable](valor: T) = println(implicitly[Printable[T]].format(valor))

    // otra forma
    def apply[T](implicit printable: Printable[T]): Printable[T] = printable

    def printApply[T: Printable](valor: T) = println(PrintableFace.apply[T].format(valor))

    def printApplyB[T: Printable](valor: T) = println(PrintableFace[T].format(valor))
  }

  def main(args: Array[String]): Unit = {

    import PrintableInstance._

    PrintableFace.print("Hola")
    PrintableFace.print(3)
    println()
    PrintableFace.printImplicitly("Hola")
    PrintableFace.printImplicitly(3)
    println()
    PrintableFace.printApply("Hola")
    PrintableFace.printApply(3)
    println()
    PrintableFace.printApplyB("Hola")
    PrintableFace.printApplyB(3)

    implicit class PrintableSyntax[T](value: T) {
      def print(implicit printable: Printable[T]) = println(printable.format(value))
    }

    "hola desde aqui".print
  }

}
