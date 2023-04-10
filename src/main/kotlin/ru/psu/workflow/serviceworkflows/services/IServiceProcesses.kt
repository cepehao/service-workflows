package ru.psu.workflow.serviceworkflows.services

import org.springframework.http.ResponseEntity
import ru.psu.workflow.serviceworkflows.model.CProcess
import java.util.*

interface IServiceProcesses
{
    fun save(process: CProcess): CProcess

    fun getAll(): Iterable<CProcess>

    fun getById(id: UUID): ResponseEntity<CProcess>

    fun deleteAll()

    fun deleteById(id: UUID): ResponseEntity<String>

}