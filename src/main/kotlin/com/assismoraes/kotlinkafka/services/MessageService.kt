package com.assismoraes.kotlinkafka.services

import com.assismoraes.kotlinkafka.domain.Message
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val kafkaMessageTemplate: KafkaTemplate<String, Message>
) {

    companion object {
        const val KAFKA_TOPIC = "quickstart-events"
    }

    fun sendToKafka(message: Message) {
        kafkaMessageTemplate.send(KAFKA_TOPIC, message)
    }
}