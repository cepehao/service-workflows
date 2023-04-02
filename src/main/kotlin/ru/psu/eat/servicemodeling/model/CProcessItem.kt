package ru.psu.eat.servicemodeling.model

import javax.persistence.*

// класс-родитель элементов процесса
@MappedSuperclass
open class CProcessItem(
    @Id
    val id: String = "",

    @Column
    val name: String = "",

    @Transient
    private var incomingIdList: ArrayList<String> = arrayListOf<String>(), // по строковым идентификаторам связей будем строить связи для
    @Transient
    private val outgoingIdList: ArrayList<String> = arrayListOf<String>() // объектов (в бд строковые идентификаторы связей хранить не будем)
)
{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id")
    var process: CProcess? = null


    // тут будут храниться ссылки на элементы процесса
    //@ManyToMany(fetch = FetchType.LAZY)
    //@JoinTable(name = "incoming_outgoing_items",
    //joinColumns = [JoinColumn(name = "incoming_item")],
    //inverseJoinColumns = [JoinColumn(name = "outgoing_item")])
    @Transient
    var incomingItemSet = mutableSetOf<CProcessItem>()


    //@ManyToMany(fetch = FetchType.LAZY, mappedBy = "incomingItemSet")
    @Transient
    var outgoingItemSet = mutableSetOf<CProcessItem>()


    fun checkIncoming(id: String): Boolean {
        return id in incomingIdList
    }

    fun checkOutgoing(id: String): Boolean {
        return id in outgoingIdList
    }

    fun getIncomingIdList(): ArrayList<String> {
        return incomingIdList
    }

    fun getOutgoingIdList(): ArrayList<String> {
        return outgoingIdList
    }
}