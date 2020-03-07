package com.knoldus.deserializer

import com.fasterxml.jackson.databind.ObjectMapper
import com.knoldus.model.User
import org.apache.kafka.common.serialization.Deserializer

class UserDeserializer extends Deserializer[User] {
  override def deserialize(arg0: String, arg1: Array[Byte]): User = {
    val mapper = new ObjectMapper
    var user = User("", -1)
    try {
      user = mapper.readValue(arg1, classOf[User])
    }
    catch {
      case e: Exception =>
        e.printStackTrace()
    }
    user
  }
}
