package com.assismoraes.kotlinkafka.domain

import com.fasterxml.jackson.annotation.JsonProperty

class Message(
    @JsonProperty("user_id")
    var userId: String,

    @JsonProperty("username")
    var username: String,

    @JsonProperty("message")
    var message: String
) {


}