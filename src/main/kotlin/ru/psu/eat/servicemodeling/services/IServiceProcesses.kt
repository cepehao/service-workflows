package ru.psu.eat.servicemodeling.services

import org.springframework.http.ResponseEntity
import ru.psu.eat.servicemodeling.model.CProcess
import java.util.*

interface IServiceProcesses {
    fun save(process: CProcess): CProcess

    fun getAll(): Iterable<CProcess>

    fun getById(id: UUID): ResponseEntity<CProcess>

    fun deleteById(id: UUID): ResponseEntity<String>


}