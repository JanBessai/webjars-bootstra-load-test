import sbt.Keys._
import sbt.Resolver
import play.twirl.sbt.Import.TwirlKeys

lazy val root = Project(id = "webjars-bootstrap-load-test", base = file("."))
  .enablePlugins(SbtTwirl)
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    moduleName := "webjars-bootstrap-load-test",

    scalaVersion := "2.12.4",

    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases")
    ),

    libraryDependencies ++= Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % "test",
      "org.webjars" %% "webjars-play" % "2.6.3",
      "org.webjars" % "bootstrap" % "4.0.0"
    ),
    sourceDirectories in (Test, TwirlKeys.compileTemplates) := Seq(
      sourceDirectory.value / "test" / "html-templates"
    ),
    sources in (Test, play.sbt.routes.RoutesKeys.routes) ++= 
      ((unmanagedResourceDirectories in Test).value * "routes").get
  )
    
