package io.strd.build.sbtssh

import jassh.SSHOptions
import sbt._

/**
  * @author chebba
  */
trait SshKeys {
  val sshOptions = settingKey[SSHOptions]("")
  val sshSession = settingKey[SshSession]("")
}
