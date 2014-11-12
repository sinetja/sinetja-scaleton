organization := "tv.cntt"

name         := "sinetja-scaleton"

version      := "1.0-SNAPSHOT"

//------------------------------------------------------------------------------

scalaVersion := "2.11.4"
//scalaVersion := "2.10.4"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

javacOptions in (Compile) ++= Seq("-Xlint:deprecation")

//------------------------------------------------------------------------------

libraryDependencies += "tv.cntt" % "sinetja" % "1.2"

// Sinetja uses SLF4J, an implementation of SLF4J is needed
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

// For writing condition in logback.xml
libraryDependencies += "org.codehaus.janino" % "janino" % "2.7.5"

libraryDependencies += "com.scalatags" %% "scalatags" % "0.4.2"

// Put config directory in classpath for easier development --------------------

// For "sbt console"
unmanagedClasspath in Compile <+= (baseDirectory) map { bd => Attributed.blank(bd / "config") }

// For "sbt run"
unmanagedClasspath in Runtime <+= (baseDirectory) map { bd => Attributed.blank(bd / "config") }

// Copy these to target/xitrum when sbt xitrum-package is run
XitrumPackage.copy("config", "public", "script")
