package ru.psu.eat.servicemodeling.model

import java.util.UUID
import javax.persistence.*

//весь процесс
@Entity
@Table(name = "processes")
class CProcess(
    @Column
    var name: String,

    @Id
    var id: UUID
)
{


    // todo посмотреть в документации hibernate как сохранять мапы в бд
    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "process")
    var tasks = mutableListOf<CTask>()

    var events = mutableMapOf<String, CEvent>()
    var gateways = mutableMapOf<String, CGateway>()


    fun addTask(id: String, task: CTask) {
        tasks.add(task)
    }

    fun addGateway(id: String, gateway: CGateway) {
        gateways[id] = gateway
    }

    fun addEvent(id: String, event: CEvent) {
        events[id] = event
    }

}