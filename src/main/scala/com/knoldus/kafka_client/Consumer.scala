package com.knoldus.kafka_client

import java.util
import java.util.Properties

import com.knoldus.ConsumerConf
import com.knoldus.model.User
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.jdk.CollectionConverters._

class Consumer {

  def consumeFromKafka(topic: String) = {
    val consumer = getConsumer
    consumer.subscribe(util.Arrays.asList(topic))
    while (true) {
      val record = consumer.poll(1000).asScala
      val msgs = record.map(data => {
        implicit val formats: DefaultFormats.type = DefaultFormats
        println(write(data.value()))
        data.value()
      }).toList
      msgs
    }
  }

  def getConsumer: KafkaConsumer[String, User] = {
    val props = new Properties()
    props.put(ConsumerConf.CONSUMER_SERVER, ConsumerConf.CONSUMER_PORT)
    props.put(ConsumerConf.KEY_DESERIALIZER, ConsumerConf.KEY_DESERIALIZER_PATH)
    props.put(ConsumerConf.VALUE_DESERIALIZER, ConsumerConf.VALUE_DESERIALIZER_PATH)
    props.put("auto.offset.reset", "latest")
    props.put("group.id", "consumer-group")
    new KafkaConsumer[String, User](props)
  }
}

object Consumer extends App {
  (new Consumer).consumeFromKafka("quick-start")
}


