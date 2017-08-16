name := "sbt-ssh"
organization := "io.strd.build"
version := "0.1.0-beta1"
description := "sbt integration for JASSH (https://github.com/dacr/jassh)"
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

sbtPlugin := true

libraryDependencies ++= Seq(
  "fr.janalyse" %% "janalyse-ssh" % "0.10.1" % "optional",
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "org.apache.commons" % "commons-compress" % "1.13")

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("com.jcraft.jsch.**" -> (organization.value + ".jsch.@1")).inAll
)
assemblyExcludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
  cp filter { af => af.data.getName.startsWith("slf4j-api") || af.data.getName.startsWith("commons-compress") }
}

bintrayRepository := "sbt-plugins"
bintrayOrganization := Some("strdio")

publishMavenStyle := false

publishArtifact in packageBin := false
addArtifact(artifact in (Compile, assembly), assembly)

scriptedSettings
scriptedLaunchOpts ++= Seq("-Xmx1024M", "-Dplugin.version=" + version.value)

scriptedBufferLog := false
