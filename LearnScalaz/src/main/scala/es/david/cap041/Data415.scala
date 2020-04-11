package es.david.cap041

object Data415 extends App {

  // Restricciones de tipo en POO
  final case class PersonOO(name: String, age: Int) {
    require(name.nonEmpty && age > 0)
  }

  // Funcionalmente definimos la clase y en el componion object devolveremos el propio objeto o el mensaje de error
  final case class Person private(name: String, age: Int)

  object Person {
    def apply(name: String, age: Int): Either[String, Person] = {
      if (name.nonEmpty && age > 0) Right(new Person(name, age))
      else Left(s"bad input: $name, $age")
    }
  }

  // definimos el metodo que entra una clase Persona y devuelve el mensaje
  def welcome(person: Person): String = s"${person.name} you look wonderful at ${person.age}!"

  // Como estamos tratando una persona que no cumple los requisitos
  // al hacer el forcomprehension nos da el mensaje del "error"
  val persona: Either[String, String] = for {
    person <- Person("", -1)
  } yield welcome(person)
  println(persona)

}
