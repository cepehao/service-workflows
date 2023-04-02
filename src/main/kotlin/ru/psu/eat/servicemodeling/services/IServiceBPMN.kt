package ru.psu.eat.servicemodeling.services

import ru.psu.eat.servicemodeling.model.CProcess
import java.io.File
import java.util.UUID

interface IServiceBPMN {

    fun parseBPMN(id: UUID): CProcess

    fun parseBPMN(id: UUID, fileBPMN: File): CProcess
}