package com.assismoraes.kotlinkafka.controllers

import com.assismoraes.kotlinkafka.domain.Message
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random


@RestController
class WSMessageController(
    private val template: SimpMessagingTemplate
) {

    @PostMapping("ws-message")
    fun send(@RequestBody message: Message): ResponseEntity<Any> {
        template.convertAndSend("/topic/message", "msg" + Random.nextInt().toString())
        return ResponseEntity.ok("message sent");
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/message")
    fun broadcastGroupMessage(@Payload message: Any): Any {
        return message
    }

}