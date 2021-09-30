package com.assismoraes.kotlinkafka.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.websocket.server.PathParam
import kotlin.random.Random

@Controller
@RequestMapping("user")
class HomeController {

    @GetMapping("create")
    fun send(@PathParam("username") username: String): String {
        val userId = "user" + Random.nextInt()
        return "redirect:/?username=$username&userId=$userId"
    }

}