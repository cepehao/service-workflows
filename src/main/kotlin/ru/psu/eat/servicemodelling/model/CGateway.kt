package ru.psu.eat.servicemodelling.model

class CGateway (
    _name: String, _incomingList: ArrayList<CIncoming>,
    _outgoingList: ArrayList<COutgoing>, _gatewayType: EGateway
)
{
    val name = _name
    val incomingList = _incomingList
    val outgoingList = _outgoingList
    val gatewayType = _gatewayType
}