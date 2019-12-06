name := """shipyard"""
organization := "com.shipyard"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies ++= Seq(evolutions, jdbc)
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.17"
libraryDependencies += "org.pac4j" % "play-pac4j" % "4.0.0"
libraryDependencies += "org.pac4j" % "pac4j-http" % "3.7.0"
libraryDependencies += "org.mockito" % "mockito-core" % "2.10.0" % "test"
