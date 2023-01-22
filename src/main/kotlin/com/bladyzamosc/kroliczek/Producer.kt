package com.bladyzamosc.kroliczek

import com.bladyzamosc.kroliczek.config.KroliczekConfig
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component


/**
 * User: Z6EKI
 * Date: 22.01.2023
 */
@Component
class Producer(private val rabbitTemplate: RabbitTemplate?) {
    fun sentMessage(message: String) {
        rabbitTemplate?.convertAndSend(
            KroliczekConfig.topicExchangeName,
            "com.bladyzamosc.kroliczek",
            message
        )
    }
}