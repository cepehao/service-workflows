package ru.psu.eat.servicemodeling.model

import javax.persistence.*

// логическое правило
@Entity
@Table(name = "gateways")
class CGateway (
    id: String = "",
    name: String = "",
    incomingIdList: ArrayList<String> = arrayListOf<String>(),
    outgoingIdList: ArrayList<String> = arrayListOf<String>(),

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    val gatewayType: EGatewayType? = null
) : CProcessItem(id, name, incomingIdList, outgoingIdList)