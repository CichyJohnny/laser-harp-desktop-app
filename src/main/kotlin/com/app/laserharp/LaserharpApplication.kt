package com.app.laserharp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class LaserharpApplication

fun main(args: Array<String>) {
    runApplication<LaserharpApplication>(*args)
}
