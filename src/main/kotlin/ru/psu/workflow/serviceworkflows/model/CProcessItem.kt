package ru.psu.workflow.serviceworkflows.model

import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

// класс-родитель элементов процесса
@Entity
@Table(name = "process_items")
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
    @Transient
    var incomingItems : MutableList<CProcessItem> = mutableListOf()


    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "process_item_relations",
        joinColumns = [JoinColumn(name = "source_id")],
        inverseJoinColumns = [JoinColumn(name = "target_id")]
    )
    var outgoingItems: MutableList<CProcessItem> = mutableListOf()


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