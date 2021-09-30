package com.assismoraes.kotlinkafka.controllers

import com.assismoraes.kotlinkafka.domain.Message
import com.assismoraes.kotlinkafka.services.MessageService
import org.springframework.http.ResponseEntity
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
    fun send(@RequestBody message: Message): ResponseEntity<Any> {
        messageService.sendToKafka(message)
        return ResponseEntity.ok("message sent");
    }

}