package com.knoldus

object ProducerConfig {
  val PRODUCER_SERVER = "bootstrap.servers"
  val PRODUCER_PORT = "localhost:9092"
  val KEY_SERIALIZER = "key.serializer"
  val KEY_SERIALIZER_PATH = "org.apache.kafka.common.serialization.StringSerializer"
  val VALUE_SERIALIZER = "value.serializer"
  val VALUE_SERIALIZER_PATH = "com.knoldus.serializers.UserSerializer"

}
