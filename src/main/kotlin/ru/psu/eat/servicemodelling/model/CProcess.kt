package ru.psu.eat.servicemodelling.model

import java.util.UUID

//весь процесс
class CProcess(_name: String, _id: UUID) {

    val name = _name
    val id = _id


    var tasks = mutableMapOf<String, CTask>()
    var events = mutableMapOf<String, CEvent>()
    var gateways = mutableMapOf<String, CGateway>()


    fun addTask(id: String, task: CTask) {
        tasks[id] = task
    }

    fun addGateway(id: String, gateway: CGateway) {
        gateways[id] = gateway
    }

    fun addEvent(id: String, event: CEvent) {
        events[id] = event
    }

}