package com.assismoraes.kotlinkafka.config

import com.assismoraes.kotlinkafka.domain.Message
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Deserializer

class MessageDeserializer : Deserializer<Message> {

    private val objectMapper = ObjectMapper()

    override fun deserialize(topic: String?, data: ByteArray?): Message {
        return objectMapper.readValue(data?.let { String(it) }, Message::class.java)
    }

}
