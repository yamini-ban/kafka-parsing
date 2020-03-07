package com.knoldus.deserializer

import java.io.{ByteArrayInputStream, ObjectInputStream}

import com.knoldus.CustomException
import com.knoldus.model.User
import org.apache.kafka.common.serialization.Deserializer

class UserDeserializer extends Deserializer[User] {
  override def deserialize(arg0: String, arg1: Array[Byte]): User = {
    try {
      val byteIn = new ByteArrayInputStream(arg1)
      val objIn = new ObjectInputStream(byteIn)
      val obj = objIn.readObject().asInstanceOf[User]
      byteIn.close()
      objIn.close()
      obj
    }
    catch {
      case e: Exception => e.printStackTrace()
        throw new CustomException("Cannot Deserialize.")
    }
  }
}
