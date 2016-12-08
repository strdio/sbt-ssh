name := "sbt-ssh"
organization := "io.strd.build"
version := "0.1.0-SNAPSHOT"

sbtPlugin := true

// janalyse-ssh
resolvers += "JAnalyse Repository" at "http://www.janalyse.fr/repository/"

libraryDependencies ++= Seq(
  "fr.janalyse" %% "janalyse-ssh" % "0.9.20-SNAPSHOT" % "optional")

Classpaths.defaultPackages.map { key =>
  publishArtifact in key := false
}

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

//artifact in (Compile, assembly) := {
//  val art = (artifact in (Compile, assembly)).value
//  art.copy(`classifier` = Some("assembly"))
//}
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
