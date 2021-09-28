package com.assismoraes.kotlinkafka.config

import com.assismoraes.kotlinkafka.domain.KafkaMessage
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Deserializer

class KafkaMessageDeserializer : Deserializer<KafkaMessage> {

    private val objectMapper = ObjectMapper()

    override fun deserialize(topic: String?, data: ByteArray?): KafkaMessage {
        return objectMapper.readValue(data?.let { String(it) }, KafkaMessage::class.java)
    }

}
