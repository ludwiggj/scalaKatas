val utest = "com.lihaoyi" %% "utest" % "0.5.4" % "test"

lazy val commonSettings = Seq(
  organization := "org.ludwiggj",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.12.3"
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "Scala Katas",
    libraryDependencies += utest,
    testFrameworks += new TestFramework("utest.runner.Framework")
  )

lazy val mixtures = project
  .settings(
    commonSettings,
    name := "Mixtures Kata",
    libraryDependencies += utest,
    testFrameworks += new TestFramework("utest.runner.Framework")
  )