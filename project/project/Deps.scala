package com.mesosphere.sbt

import sbt._

object Deps {

  type EncodedArtifactID = (String, Boolean)
  type EncodedModuleID = (String, (EncodedArtifactID, String))
  // The plugins that BuildPlugin will provide to builds where it is used
  val plugins: Seq[ModuleID] = Seq(
    "org.scala-sbt.plugins" % "sbt-onejar" % "0.8", // MIT
    "org.scoverage" % "sbt-scoverage" % "1.5.1" // APLv2.0
  )
  val libraries: Seq[ModuleID] = Seq(
    "org.apache.curator" % "curator-recipes" % curatorVersion, // APLv2.0
    "org.apache.curator" % "curator-test" % curatorVersion, // APLv2.0
    "org.scalastyle" %% "scalastyle" % "1.0.0", // APLv2.0
    "org.javassist" % "javassist" % "3.20.0-GA"
  )
  private val curatorVersion = "2.9.1"

  def buildInfoEncode(id: ModuleID): EncodedModuleID = {
    // Need to use nested Tuple2s; sbt-buildinfo doesn't handle arbitrary TupleNs
    val artifactId = (id.name, id.crossVersion.toString == CrossVersion.binary.toString)
    (id.organization, (artifactId, id.revision))
  }

}
