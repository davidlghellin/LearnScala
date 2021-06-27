name := "LearnScala"


lazy val dependencies =
  new {
    val scalazVersion = "7.2.26"
    val refinedVersion = "0.9.4"
    val monocleVersion = "2.0.0"

    val refined = "eu.timepit" %% "refined" % refinedVersion

    val scalaZCore = "org.scalaz" %% "scalaz-core" % scalazVersion
    val scalaZEffect = "org.scalaz" %% "scalaz-effect" % scalazVersion
    val scalaZTypelevel = "org.scalaz" %% "scalaz-typelevel" % scalazVersion

    val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test
    val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
    val scalaMock = "org.scalamock" %% "scalamock" % "4.0.0" % "test"

    val simulacrum = "com.github.mpilquist" %% "simulacrum" % "0.13.0"

    val shapeless = "com.chuusai" %% "shapeless" % "2.3.3"
    val catsCore = "org.typelevel" %% "cats-core" % "1.0.0"
    val cats = "org.typelevel" %% "cats" % "0.9.0"

    val scalactic = "org.scalactic" %% "scalactic" % "3.0.4"
    val monocleCore = "com.github.julien-truffaut" %% "monocle-core" % monocleVersion
    val monocleMacro = "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion
    val monocleTest = "com.github.julien-truffaut" %% "monocle-law" % monocleVersion % "test"

    // https://mvnrepository.com/artifact/com.chuusai/shapeless
    val shapeless2 = "com.chuusai" %% "shapeless" % "2.4.0-M1"
  }
lazy val commonDependencies = Seq(
  dependencies.scalaTest
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")

//ThisBuild / shellPrompt := { state => s"aaaaa{prompt(projectName(state))}> " }

lazy val LearnScalaAll = (project in file("."))
  .enablePlugins(LocPlugin)
  .settings(
    commonSettings
  )
  .aggregate(
    Categoria
    , LearnCat
    , LearnScalaz
    , LearnShapeless
    , LearnRefined
    , LearnZIO
    , LibreriasUtils
    , Listas
    , ProgFun
    , TypeClases
    , Sofp
  )

lazy val commonSettings = Seq(
  scalacOptions += "-language:_",
  scalacOptions += "-Ypartial-unification",

  //https://scalacenter.github.io/scalafix/docs/rules/RemoveUnused.html
  scalacOptions += "-Xfatal-warnings",
  // scalacOptions += "-Ywarn-unused-import", // los import que no se usan los detecta

  //https://docs.scala-lang.org/overviews/compiler-options/index.html
  scalacOptions ++= Seq(
    "-encoding"
    , "utf8"
    , "-explaintypes"
  ),
  sbtPlugin := true,
  version := "0.1-SNAPSHOT",
  organization := "es.david",
  scalaVersion := "2.12.8"
  //  test in assembly := {}
  , addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.7")
  , addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
)


//lazy val Libreria = (project in file("Libreria"))
//  .settings(
//    commonSettings,
//    libraryDependencies ++= Seq(
//      scalaTest
//    )
//  )
//
//
//lazy val Proyecto = (project in file("Proyecto"))
//  .settings(
//    commonSettings,
//    mainClass in assembly := Some("Saluda"),
//    //  mainClass in (Compile, run):= Some("Saluda"),
//    name := "proyecto",
//    libraryDependencies ++= Seq(
//      scalaTest
//    )
//  )
//  .dependsOn(Libreria)

lazy val Listas = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies
  )

lazy val ProgFun = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies ++ Seq(
        dependencies.scalactic
        , dependencies.scalaMock
        , dependencies.scalaCheck
        , dependencies.shapeless
        , dependencies.cats
      )
  )

lazy val Categoria = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies ++ Seq(
        dependencies.scalactic
        , dependencies.scalaMock
        , dependencies.scalaCheck
      )
  )

lazy val TypeClases = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies ++ Seq(
        dependencies.scalactic
        , dependencies.catsCore
        , dependencies.scalaMock
        , dependencies.scalaCheck
      )
  )

lazy val LearnLens = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies ++ Seq(
        dependencies.monocleCore
        , dependencies.monocleMacro
        , dependencies.monocleTest
      )
  )
lazy val LearnScalaz = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies ++ Seq(
        dependencies.simulacrum
        , dependencies.scalaZCore
        // , dependencies.scalaZTypelevel
        , dependencies.scalaZEffect
        , dependencies.refined
        , "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
        // https://mvnrepository.com/artifact/org.scalaz/scalaz-concurrent
        , "org.scalaz" %% "scalaz-concurrent" % "7.2.30"
        , "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
        , "ch.qos.logback" % "logback-classic" % "1.2.3"
      )
  )

lazy val LearnShapeless = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies ++ Seq(
        dependencies.refined
        , "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
        // https://mvnrepository.com/artifact/org.scalaz/scalaz-concurrent
        , "ch.qos.logback" % "logback-classic" % "1.2.3"
        , dependencies.shapeless2
      )
  )

lazy val LearnRefined = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies ++ Seq(
        dependencies.refined
      )
  )

lazy val LearnZIO = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies ++ Seq(
        "dev.zio" %% "zio" % "1.0.0-RC18-2"
        , "dev.zio" %% "zio-logging" % "0.2.7"

        , "dev.zio" %% "zio-logging" % "0.2.7"
        , "dev.zio" %% "zio-test" % "1.0.0-RC18-2" % "test"
        , "dev.zio" %% "zio-test-sbt" % "1.0.0-RC18-2" % "test"
        //     ,"dev.zio" %% "zio-test-magnolia" % zioVersion % "test" // optional
        , "dev.zio" %% "zio-config" % "1.0.0"
        , "dev.zio" %% "zio-config-magnolia" % "1.0.0"
        ,"dev.zio" %% "zio-config-typesafe" % "1.0.0"
      )
  )

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

lazy val LibreriasUtils = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies ++ Seq(
        "org.rogach" %% "scallop" % "3.4.0"
      )
  )

lazy val Sofp = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies ++ Seq(
      )
  )

val catsVersion = "2.1.1"
lazy val LearnCat = project
  .settings(
    commonSettings,
    libraryDependencies ++=
      commonDependencies ++ Seq(
        "org.typelevel" %% "cats-core" % catsVersion
      )
  )

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
