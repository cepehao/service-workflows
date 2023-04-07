package ru.psu.workflow.serviceworkflows

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServiceModellingApplication

fun main(args: Array<String>) {
	runApplication<ServiceModellingApplication>(*args)
}
