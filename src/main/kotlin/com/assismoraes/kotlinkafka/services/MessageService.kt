package com.assismoraes.kotlinkafka.services

import com.assismoraes.kotlinkafka.domain.Message
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class MessageService(
    private val kafkaMessageTemplate: KafkaTemplate<String, Message>
) {

    companion object {
        const val KAFKA_TOPIC = "kotlin-kafka.chat"
    }

    fun sendToKafka(message: Message) {
        kafkaMessageTemplate.send(KAFKA_TOPIC, message)
    }
}