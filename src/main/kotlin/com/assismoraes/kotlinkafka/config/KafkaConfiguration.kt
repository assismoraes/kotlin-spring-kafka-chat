package com.assismoraes.kotlinkafka.config

import com.assismoraes.kotlinkafka.domain.KafkaMessage
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
@EnableKafka
class KafkaConfiguration {

    @Bean
    fun producerFactory(): ProducerFactory<String, KafkaMessage> {
        val config: MutableMap<String, Any> = HashMap()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "127.0.0.1:9092"
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory<String, KafkaMessage>(config)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, KafkaMessage> {
        return KafkaTemplate<String, KafkaMessage>(producerFactory())
    }

    @Bean
    fun kafkaListenerContainerFactory(consumerFactory: ConsumerFactory<String, KafkaMessage>): ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> =
            ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory
        return factory
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, KafkaMessage> {
        val props = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "127.0.0.1:9092",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to KafkaMessageDeserializer::class.java,
        )
        return DefaultKafkaConsumerFactory(props)
    }

}