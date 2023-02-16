package ru.psu.eat.servicemodelling.model

class CEvent (
    _name: String, _incomingList: ArrayList<CIncoming>,
    _outgoingList: ArrayList<COutgoing>, _eventType: EEventType
)
{
    val name = _name
    val incomingList = _incomingList
    val outgoingList = _outgoingList
    val eventType = _eventType
}