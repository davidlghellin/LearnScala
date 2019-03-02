package es.david

final case class Url(value: String) extends AnyVal
final case class GitHash(value: String) extends AnyVal
final case class Config(url: Url, gitHash: GitHash)

// http://fthomas.github.io/talks/2016-05-04-refined/
object Main extends App {
  //  Config(GitHash("c4943b6"), Url("file:/opt/app")) // no compila
  Config(Url("c4943b6"), GitHash("file:/opt/app")) // compila pero es un bug ya que estan cambiados

  // caso 2
  // creamos el constructor privado y creamos una factoria solo para hash validos
  val p1 = test.GitHash.fromString("c484b4")
  println(p1)
  val p2 = test.GitHash.fromString("xyz")
  println(p2)

  // no tenemos acceso al constructor
  //  val noPuedo = new test.GitHash("c484b4")
  //  println(noPuedo)

  println(test.GitHash.fromString("c4943b6"))
  println(test.GitHash.fromString("c4943b6").get) // no obtener el valor a menos que sea necesiaro
}

object test {

  final case class GitHash private(value: String) extends AnyVal

  object GitHash {
    def fromString(s: String): Option[GitHash] = {
      def isGitHash(s: String): Boolean =
        (s.length >= 4 && s.length <= 40 &&
          s.forall(c => c.isDigit || ('a' to 'f').contains(c)))

      if (isGitHash(s)) Some(new GitHash(s)) else None
    }
  }

}