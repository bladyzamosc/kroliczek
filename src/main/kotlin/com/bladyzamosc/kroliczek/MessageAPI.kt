package com.bladyzamosc.kroliczek

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * User: Z6EKI
 * Date: 23.01.2023
 */
@RestController()
@RequestMapping("messages")
class MessageAPI(private val producer: Producer) {

    @PostMapping
    fun sendMessage(@RequestBody message: String): String {
        producer.sentMessage(message)
        return message
    }
}