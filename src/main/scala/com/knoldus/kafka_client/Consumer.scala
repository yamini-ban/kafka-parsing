package com.knoldus.kafka_client

import java.util
import java.util.Properties
import scala.jdk.CollectionConverters._
import com.knoldus.model.User
import org.apache.kafka.clients.consumer.KafkaConsumer

class Consumer {
  def consumeFromKafka(topic: String) = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "com.knoldus.deserializer.UserDeserializer")
    props.put("auto.offset.reset", "latest")
    props.put("group.id", "consumer-group")
    val consumer: KafkaConsumer[String, User] = new KafkaConsumer[String, User](props)
    consumer.subscribe(util.Arrays.asList(topic))
    while (true) {
      val record = consumer.poll(1000).asScala
      for (data <- record.iterator) {
        println(data.value())
      }
    }
  }
}

object Consumer extends App {
  (new Consumer).consumeFromKafka("quick-start")
}


