package ru.psu.workflow.serviceworkflows.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.UUID
import javax.persistence.*

//весь процесс
@Entity
@Table(name = "processes")
class CProcess(

    @Id
    var id: UUID? = null,

    @Column
    var name: String = ""

)
{
    @JsonIgnore
    @OneToMany(mappedBy = "process", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var tasks = mutableMapOf<String, CTask>()

    @JsonIgnore
    @OneToMany(mappedBy = "process", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var events = mutableMapOf<String, CEvent>()

    @JsonIgnore
    @OneToMany(mappedBy = "process", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
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