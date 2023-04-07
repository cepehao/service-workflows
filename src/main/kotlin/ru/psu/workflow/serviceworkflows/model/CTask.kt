package ru.psu.workflow.serviceworkflows.model

import javax.persistence.*

//задача
@Entity
@Table(name = "tasks")
class CTask(
    id: String = "",
    name: String = "",
    incomingIdList: ArrayList<String> = arrayListOf<String>(),
    outgoingIdList: ArrayList<String> = arrayListOf<String>()
) : CProcessItem(id, name, incomingIdList, outgoingIdList)
