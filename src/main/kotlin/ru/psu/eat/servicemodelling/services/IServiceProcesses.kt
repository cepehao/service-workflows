package ru.psu.eat.servicemodelling.services

import ru.psu.eat.servicemodelling.model.CProcess

interface IServiceProcesses {
    fun save(item: CProcess)
}