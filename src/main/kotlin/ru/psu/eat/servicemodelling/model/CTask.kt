package ru.psu.eat.servicemodelling.model

//задача
class CTask(
    _name:String, _incomingList: ArrayList<CIncoming>, _outgoingList: ArrayList<COutgoing>,
)
{

    val name = _name
    val incomingList = _incomingList
    val outgoingList = _outgoingList

}