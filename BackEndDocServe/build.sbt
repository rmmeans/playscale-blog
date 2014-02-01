name := "BackEndDocServe"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "commons-codec" % "commons-codec" % "1.6+"
)     

play.Project.playJavaSettings
