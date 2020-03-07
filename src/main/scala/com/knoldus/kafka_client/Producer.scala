package com.knoldus.kafka_client

import java.util.Properties

import com.knoldus.ProducerConfig
import com.knoldus.model.User
import net.liftweb.json.{DefaultFormats, parse}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.io.Source

class Producer {
  def writeToKafka(topic: String): Unit = {
    val props = new Properties()
    props.put(ProducerConfig.PRODUCER_SERVER, ProducerConfig.PRODUCER_PORT )
    props.put(ProducerConfig.KEY_SERIALIZER, ProducerConfig.KEY_SERIALIZER_PATH)
    props.put(ProducerConfig.VALUE_SERIALIZER, ProducerConfig.VALUE_SERIALIZER_PATH)
    val producer = new KafkaProducer[String, User](props)
    val source = Source.fromFile("./src/main/resources/sample-user.txt")
    val content = source.getLines().mkString
    val data = parse(content)
    implicit val format: DefaultFormats.type = DefaultFormats
    val user = data.extract[User]
    println(user)
    val record = new ProducerRecord[String, User](topic, "key", user)
    producer.send(record)
    producer.close()
  }
}

object Producer extends App {
  val x = new Producer
  x.writeToKafka("quick-start")
}
