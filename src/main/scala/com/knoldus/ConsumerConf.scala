package com.knoldus

object ConsumerConf {
  val CONSUMER_SERVER = "bootstrap.servers"
  val CONSUMER_PORT = "localhost:9092"
  val KEY_DESERIALIZER = "key.deserializer"
  val KEY_DESERIALIZER_PATH = "org.apache.kafka.common.serialization.StringDeserializer"
  val VALUE_DESERIALIZER = "value.deserializer"
  val VALUE_DESERIALIZER_PATH = "com.knoldus.deserializer.UserDeserializer"
}
