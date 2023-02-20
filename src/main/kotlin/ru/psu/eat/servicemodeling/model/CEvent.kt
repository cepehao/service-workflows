package ru.psu.eat.servicemodeling.model

class CEvent (
    name: String,
    incoming: ArrayList<CProcessItem>,
    outgoing: ArrayList<CProcessItem>,
    var type: EEventType
) : CProcessItem(name, incoming, outgoing)