organization := "tv.cntt"
name         := "sinetja-scaleton"
version      := "1.0-SNAPSHOT"

//------------------------------------------------------------------------------

scalaVersion := "2.12.2"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")
javacOptions in Compile ++= Seq("-Xlint:deprecation")

//------------------------------------------------------------------------------

libraryDependencies += "tv.cntt" % "sinetja" % "1.3.0"

// Sinetja uses SLF4J, an implementation of SLF4J is needed
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.2"

// For writing condition in logback.xml
libraryDependencies += "org.codehaus.janino" % "janino" % "3.0.7"

libraryDependencies += "com.lihaoyi" %% "scalatags" % "0.6.5"

// Put config directory in classpath for easier development --------------------

// For "sbt console"
unmanagedClasspath in Compile += Attributed.blank(baseDirectory.value / "config")

// For "sbt run"
unmanagedClasspath in Runtime += Attributed.blank(baseDirectory.value / "config")

// Copy these to target/xitrum when sbt xitrum-package is run
XitrumPackage.copy("config", "public", "script")
