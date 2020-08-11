import Util._

addCommandAlias("c", "compile")
addCommandAlias("ca", "test:compile")
addCommandAlias("t", "test")
addCommandAlias("r", "run")

onLoadMessage +=
  s"""|
      |───────────────────────────
      |  List of defined ${styled("aliases")}
      |────────┬──────────────────
      |${styled("c")}       │ compile
      |${styled("ca")}      │ compile all
      |${styled("t")}       │ test
      |${styled("r")}       │ run
      |────────┴──────────────────""".stripMargin