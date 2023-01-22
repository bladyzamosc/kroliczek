package com.bladyzamosc.kroliczek

import org.springframework.stereotype.Component

/**
 * User: Z6EKI
 * Date: 22.01.2023
 */
@Component
class Consumer {

    fun consumeMessage(message: String) {
        println("Received <$message>")
    }
}