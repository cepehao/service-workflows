package ru.psu.eat.servicemodeling.model

import javax.persistence.*

//задача
@Entity
@Table(name = "tasks")
class CTask(
    name: String,
    incoming: ArrayList<CProcessItem>,
    outgoing: ArrayList<CProcessItem>,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "process_id")
    val process: CProcess? = null
) : CProcessItem(name, incoming, outgoing)
