package ru.psu.eat.servicemodeling.model

class CGateway (
    name: String,
    incoming: ArrayList<CProcessItem>,
    outgoing: ArrayList<CProcessItem>,
    var type: EGatewayType
) : CProcessItem(name, incoming, outgoing)