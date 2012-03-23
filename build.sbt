

name := "Java8vsScala"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
    "org.slf4j" % "slf4j-api" % "1.6.1",
        "ch.qos.logback" % "logback-classic" % "0.9.28",
	"org.specs2" %% "specs2" % "1.8.2" % "test",
	"junit" % "junit" % "4.8.1",
	"net.databinder" %% "dispatch-tagsoup" % "0.8.8",
	"commons-io" % "commons-io" % "1.3.2",
	"net.databinder" %% "dispatch-http" % "0.8.8"
)

