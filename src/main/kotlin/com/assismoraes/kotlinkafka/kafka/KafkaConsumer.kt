package com.assismoraes.kotlinkafka.kafka

import com.assismoraes.kotlinkafka.domain.KafkaMessage
import com.assismoraes.kotlinkafka.services.MessageService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {

    @KafkaListener(topics = [ MessageService.KAFKA_TOPIC ], groupId = "group_json")
    fun consume(kafkaMessage: KafkaMessage) {
        println("to: " + kafkaMessage.to + " | content: " + kafkaMessage.content)
    }

}