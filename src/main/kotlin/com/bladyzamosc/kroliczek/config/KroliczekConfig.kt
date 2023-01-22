package com.bladyzamosc.kroliczek.config

import com.bladyzamosc.kroliczek.Consumer
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * User: Z6EKI
 * Date: 22.01.2023
 */
@Configuration
class KroliczekConfig {

    companion object {
        val topicExchangeName = "kroliczek-topic"
        val queueName = "kroliczek-queue"
    }

    @Bean
    fun queue(): Queue? {
        return Queue(queueName, false)
    }

    @Bean
    fun exchange(): TopicExchange? {
        return TopicExchange(topicExchangeName)
    }

    @Bean
    fun binding(queue: Queue?, exchange: TopicExchange?): Binding? {
        return BindingBuilder.bind(queue).to(exchange).with("com.bladyzamosc.#")
    }


    @Bean
    fun listenerAdapter(consumer: Consumer?): MessageListenerAdapter? {
        return MessageListenerAdapter(consumer, "consumeMessage")
    }

    @Bean
    fun container(
        connectionFactory: ConnectionFactory?,
        listenerAdapter: MessageListenerAdapter?
    ): SimpleMessageListenerContainer? {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory!!
        container.setQueueNames(queueName)
        container.setMessageListener(listenerAdapter!!)
        return container
    }

}