package com.knoldus.serializers

import com.fasterxml.jackson.databind.ObjectMapper
import com.knoldus.model.User
import org.apache.kafka.common.serialization.Serializer

class UserSerializer extends Serializer[User] {
  override def serialize(topic: String, data: User): Array[Byte] = {
      var retVal: Array[Byte] = Array.empty[Byte]
      val objectMapper = new ObjectMapper
      try {
        retVal = objectMapper.writeValueAsString(data).getBytes
      }
      catch {
        case e: Exception => e.printStackTrace()
      }
      retVal
    }
}
