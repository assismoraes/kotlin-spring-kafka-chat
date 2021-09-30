package com.assismoraes.kotlinkafka.controllers

import com.assismoraes.kotlinkafka.domain.Message
import com.assismoraes.kotlinkafka.services.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam
import kotlin.random.Random


@Controller
@RequestMapping("user")
class HomeController(
    private val messageService: MessageService,
) {

    @GetMapping("create")
    fun send(@PathParam("username") username: String): String {
        val userId = "user" + Random.nextInt()
        return "redirect:/?username=$username&userId=$userId"
    }

}