package com.assismoraes.kotlinkafka.kafka

import com.assismoraes.kotlinkafka.domain.Message
import com.assismoraes.kotlinkafka.services.MessageService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(
    private val template: SimpMessagingTemplate
) {

    @KafkaListener(topics = [ MessageService.KAFKA_TOPIC ], groupId = "default_group")
    fun consume(message: Message) {
        template.convertAndSend("/topic/message", message)
    }

}