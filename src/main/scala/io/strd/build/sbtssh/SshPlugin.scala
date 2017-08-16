package io.strd.build.sbtssh

import fr.janalyse.ssh.{SSH, SSHOptions}
import sbt._
import sbt.plugins.JvmPlugin

/**
  * @author chebba
  */
object SshPlugin extends AutoPlugin {

  override def requires: Plugins = JvmPlugin
  override def trigger: PluginTrigger = allRequirements

  object autoImport extends SshKeys

  import autoImport._

  override def projectSettings: Seq[Setting[_]] = Seq(
    sshOptions := SSHOptions(),
    sshSession := createSession(sshOptions.value)
  )

  def sshConfig(config: Configuration) = Seq(
    sshOptions in config := sshOptions.value,
    sshSession in config := createSession((sshOptions in config).value)
  )

  def createSession(opts: SSHOptions): SshSession = new SshSession {
    def withSsh[R](f: (SSH) => R): R = SSH.once(opts)(f)
  }
}

trait SshSession {
  def withSsh[R](f: SSH => R): R
}
