resolvers += Resolver.url("artifactory",url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.5")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
