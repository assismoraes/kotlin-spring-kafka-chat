package com.assismoraes.kotlinkafka.services

import com.assismoraes.kotlinkafka.domain.KafkaMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val kafkaMessageKafkaTemplate: KafkaTemplate<String, KafkaMessage>
) {

    companion object {
        const val KAFKA_TOPIC = "quickstart-events"
    }

    fun send(kafkaMessage: KafkaMessage) {
        kafkaMessageKafkaTemplate.send(KAFKA_TOPIC, kafkaMessage)
    }
}