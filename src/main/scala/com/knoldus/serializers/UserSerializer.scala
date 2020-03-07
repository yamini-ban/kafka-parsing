package com.knoldus.serializers

import java.io.{ByteArrayOutputStream, ObjectOutputStream}

import com.knoldus.CustomException
import com.knoldus.model.User
import org.apache.kafka.common.serialization.Serializer

class UserSerializer extends Serializer[User] {
  override def serialize(topic: String, data: User): Array[Byte] = {
    try {
      val byteOut = new ByteArrayOutputStream()
      val objOut = new ObjectOutputStream(byteOut)
      objOut.writeObject(data)
      objOut.close()
      byteOut.close()
      byteOut.toByteArray
    }
    catch {
      case e: Exception => e.printStackTrace()
        throw new CustomException("Cannot Serialize.")
    }
  }
}
