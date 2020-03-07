package com.knoldus.kafka_client

import java.util.Properties

import com.knoldus.ProducerConfig
import com.knoldus.model.User
import net.liftweb.json.{DefaultFormats, parse}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.io.Source

class Producer {

  def writeToKafka(topic: String, getDataFromSourcePath: String): Unit = {
    val user = getJsonDataFromFile(getDataFromSourcePath)
    val record = new ProducerRecord[String, User](topic, "key", user)
    getProducer.send(record)
    getProducer.close()
  }

  def getProducer: KafkaProducer[String, User] = {
    val props = new Properties()
    props.put(ProducerConfig.PRODUCER_SERVER, ProducerConfig.PRODUCER_PORT)
    props.put(ProducerConfig.KEY_SERIALIZER, ProducerConfig.KEY_SERIALIZER_PATH)
    props.put(ProducerConfig.VALUE_SERIALIZER, ProducerConfig.VALUE_SERIALIZER_PATH)
    new KafkaProducer[String, User](props)
  }

  def getJsonDataFromFile(path: String): User = {
    val source = Source.fromFile(path) //
    val content = source.getLines().mkString
    val data = parse(content)
    implicit val format: DefaultFormats.type = DefaultFormats
    data.extract[User]
  }
}

object Producer extends App {
  val x = new Producer
  x.writeToKafka("quick-start", "./src/main/resources/sample-user.txt")
}
