name := "LearnScala"

//organization in ThisBuild := "es.david"
//version in ThisBuild := "1.0-SNAPSHOT"
//scalaVersion in ThisBuild := "2.12.8"
////scalaVersion := "2.12.8"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `introlagom` = (project in file("."))
  .settings(
    commonSettings,
  )
  .aggregate(`hola`, `Listas`, `ProgFun`, `Categoria`, `TypeClases`, `LearnScalaz`, `LearnRefined`)

lazy val commonSettings = Seq(
  scalacOptions += "-language:_",
  scalacOptions += "-Ypartial-unification",

  //https://scalacenter.github.io/scalafix/docs/rules/RemoveUnused.html
  scalacOptions += "-Xfatal-warnings",
  // scalacOptions += "-Ywarn-unused-import", // los import que no se usan los detecta

  //https://docs.scala-lang.org/overviews/compiler-options/index.html
  scalacOptions ++= Seq("-encoding", "utf8",
    "-explaintypes"),

  version := "0.1-SNAPSHOT",
  organization := "es.david",
  scalaVersion := "2.12.8"
  //  test in assembly := {} 
)


lazy val `hola` = (project in file("hola"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      scalaTest
    )
  )


lazy val `Listas` = (project in file("Listas"))
  .settings(
    commonSettings,
    mainClass in assembly := Some("Saluda"),
  //  mainClass in (Compile, run):= Some("Saluda"),
    name := "proyecto",
    libraryDependencies ++= Seq(
      scalaTest
    )
  )
  .dependsOn(`hola`)

lazy val `ProgFun` = (project in file("ProgFun"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      scalaTest,
      "org.scalactic" %% "scalactic" % "3.0.4",
      "org.scalamock" %% "scalamock" % "4.0.0" % "test",
      "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
    )
  )

lazy val `Categoria` = (project in file("Categoria"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      scalaTest,
      "org.scalactic" %% "scalactic" % "3.0.4",
      "org.scalamock" %% "scalamock" % "4.0.0" % "test",
      "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
    )
  )

lazy val `TypeClases` = (project in file("TypeClases"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      scalaTest,
      "org.scalactic" %% "scalactic" % "3.0.4",
      "org.scalamock" %% "scalamock" % "4.0.0" % "test",
      "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
    )
  )

lazy val `LearnScalaz` = (project in file("LearnScalaz"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      scalaTest,
      "com.github.mpilquist" %% "simulacrum" % "0.13.0",
      "org.scalaz" %% "scalaz-core" % "7.2.26"
    )
  )

lazy val `LearnRefined` = (project in file("LearnRefined"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      scalaTest,
      "eu.timepit" %% "refined" % "0.9.4"
    )
  )

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.7")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

////
import scala.sys.process._

lazy val hello = taskKey[Unit]("Prints 'Hello World'")

hello := println("hello world!")

lazy val execScript = taskKey[Unit]("Execute the shell script")

execScript := {
  "clean_target.sh" !
}

// https://code.i-harness.com/en/q/17d6a55
lazy val deleteClass = taskKey[Unit]("Execute frontend scripts")

deleteClass := {
  val s: TaskStreams = streams.value
  val shell: Seq[String] = if (sys.props("os.name").contains("Windows")) Seq("cmd", "/c") else Seq("bash", "-c")
  val removeFiles: Seq[String] = shell :+ "/bin/bash ./clean_target.sh"
  val ls: Seq[String] = shell :+ "ls ."
  s.log.info("Borrando class...")
  //  if((removeFiles  !) == 0) {
  //    s.log.success("Borrado ficheros successful!")
  //  } else {
  //    s.log.error("Error!")
  //    throw new IllegalStateException("frontend build failed!")
  //  }

  s.log.info("Borrando ficheros successful!")
  removeFiles !
}

lazy val compressTar = taskKey[Unit]("Compres in tar")

compressTar := {
  val s: TaskStreams = streams.value
  val shell: Seq[String] = if (sys.props("os.name").contains("Windows")) Seq("cmd", "/c") else Seq("bash", "-c")
  val compress: Seq[String] = shell :+ "/bin/bash ./compress_tar.sh"
  s.log.info("Comprimiendo")
  compress !

  s.log.success("Completado ")
}

name in Compile := "hello"