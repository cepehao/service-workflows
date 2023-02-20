package ru.psu.eat.servicemodeling.model

open class CProcessItem (
    var name: String,
    var incoming: ArrayList<CProcessItem>,
    var outgoing: ArrayList<CProcessItem>
)