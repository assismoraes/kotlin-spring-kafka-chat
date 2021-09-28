package com.assismoraes.kotlinkafka.domain

import com.fasterxml.jackson.annotation.JsonProperty

class KafkaMessage(
    @JsonProperty("to")
    var to: String,

    @JsonProperty("content")
    var content: String
) {


}