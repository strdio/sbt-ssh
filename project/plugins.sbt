addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.5")
addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")

libraryDependencies += { "org.scala-sbt" % "scripted-plugin" % sbtVersion.value }
