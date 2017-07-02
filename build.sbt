name := "sbt-ssh"
organization := "io.strd.build"
version := "0.1.0-beta1"
description := "sbt integration for janalyse-ssh"
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

sbtPlugin := true
publishMavenStyle := false

bintrayRepository := "sbt-plugins-snapshots"
bintrayOrganization in bintray := Some("strdio")

libraryDependencies ++= Seq(
  "fr.janalyse" %% "janalyse-ssh" % "0.10.1" % "optional")

Classpaths.defaultPackages.map { key =>
  publishArtifact in key := false
}

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

addArtifact(artifact in (Compile, assembly), assembly)

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("com.jcraft.jsch.**" -> (organization.value + ".jsch.@1")).inAll
)

scriptedSettings
scriptedLaunchOpts := {
  scriptedLaunchOpts.value ++
  Seq("-Xmx1024M", "-XX:MaxPermSize=256M", "-Dplugin.version=" + version.value)
}
scriptedBufferLog := false
