package ru.psu.workflow.serviceworkflows.services

import ru.psu.workflow.serviceworkflows.model.CProcess
import java.io.File
import java.util.UUID

interface IServiceBPMN {

    fun parseBPMN(id: UUID): CProcess

    fun parseBPMN(id: UUID, fileBPMN: File): CProcess
}