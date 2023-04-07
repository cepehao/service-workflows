package ru.psu.workflow.serviceworkflows.model

import javax.persistence.*

// событие
@Entity
@Table(name = "events")
class CEvent  (
    id: String = "",
    name: String = "",
    incomingIdList: ArrayList<String> = arrayListOf<String>(),
    outgoingIdList: ArrayList<String> = arrayListOf<String>(),

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    val eventType: EEventType? = null
) : CProcessItem(id, name, incomingIdList, outgoingIdList)
