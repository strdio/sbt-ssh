sbt-ssh
============================================
Ssh client for sbt which integrates for JASSH (https://github.com/dacr/jassh)

[ ![Download](https://api.bintray.com/packages/strdio/sbt-plugins/sbt-ssh/images/download.svg) ](https://bintray.com/strdio/sbt-plugins/sbt-ssh/_latestVersion)

## Installation ##
This plugin uses JASSH v0.10.0+ and requires at least **Java 8**.

Add the following to your `project/plugins.sbt` file:

    addSbtPlugin("io.strd.build" % "sbt-ssh" % "0.1.0")

## Usage ##
```scala
sshOptions := sshOptions.value.copy(
  host = "example.com",
  username = "me")
  
customTask := {
  val session = sshSession.value
  
  session.withSsh { ssh =>
    ssh.shell { shell =>
      import shell._
      put("test", "/tmp/ssh.test")
      val res = get("/tmp/ssh.test")
      rm("/tmp/ssh.test")
      res.exists(_ == "test")
    }
  }
}
```
Please read the [JASSH documentation](https://github.com/dacr/jassh) for an additional information. 
