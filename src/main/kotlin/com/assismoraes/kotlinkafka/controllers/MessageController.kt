package com.assismoraes.kotlinkafka.controllers

import com.assismoraes.kotlinkafka.domain.KafkaMessage
import com.assismoraes.kotlinkafka.services.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("message")
class MessageController(
    private val messageService: MessageService,
) {

    @PostMapping
    fun send(@RequestBody kafkaMessage: KafkaMessage): ResponseEntity<Any> {
        messageService.send(kafkaMessage)
        return ResponseEntity.ok("message sent");
    }

}