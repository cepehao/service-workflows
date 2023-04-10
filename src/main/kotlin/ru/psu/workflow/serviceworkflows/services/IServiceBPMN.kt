package ru.psu.workflow.serviceworkflows.services

import org.springframework.web.multipart.MultipartFile
import ru.psu.workflow.serviceworkflows.model.CProcess
import java.util.UUID

interface IServiceBPMN
{
    fun parseBPMN(id: UUID?, fileBPMN: MultipartFile): CProcess
}