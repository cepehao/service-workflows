package ru.psu.eat.servicemodeling.services

import ru.psu.eat.servicemodeling.model.CProcess

interface IServiceProcesses {
    fun save(item: CProcess) : CProcess
}