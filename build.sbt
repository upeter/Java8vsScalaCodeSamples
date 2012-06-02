

name := "LiveCodingScalaSolution"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
    "org.slf4j" % "slf4j-api" % "1.6.1",
        "ch.qos.logback" % "logback-classic" % "0.9.28",
	"org.specs2" %% "specs2" % "1.10" % "test",
	"junit" % "junit" % "4.8.1",
	"net.databinder" %% "dispatch-tagsoup" % "0.8.8",
	"commons-io" % "commons-io" % "1.3.2",
	"com.google.guava" % "guava" % "10.0.1",
	"net.databinder" %% "dispatch-http" % "0.8.8"
)

