name := "kafka-json"

version := "0.1"

scalaVersion := "2.13.1"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka
libraryDependencies += "org.apache.kafka" %% "kafka" % "2.4.0"

libraryDependencies += "net.liftweb" %% "lift-json" % "3.4.0"
