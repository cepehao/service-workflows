package ru.psu.eat.servicemodelling.model

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

//весь процесс
@Entity
@Table(name = "processes")
class CProcess(_name: String, _id: UUID) {
    @Id
    val id = _id
    @Column
    val name = _name

    // todo посмотреть в документации hibernate как сохранять мапы в бд
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