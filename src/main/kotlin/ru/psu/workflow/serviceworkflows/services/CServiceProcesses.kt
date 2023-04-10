package ru.psu.workflow.serviceworkflows.services

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.psu.workflow.serviceworkflows.model.CProcess
import ru.psu.workflow.serviceworkflows.repositories.IRepositoryProcesses
import java.util.*

@Service
class CServiceProcesses(val repositoryProcesses: IRepositoryProcesses): IServiceProcesses
{
    override fun save(process: CProcess): CProcess {
        return repositoryProcesses.save(process)
    }

    override fun getAll(): Iterable<CProcess> {
        return repositoryProcesses.findAll()
    }

    override fun getById(id: UUID): ResponseEntity<CProcess> {
        return repositoryProcesses.findById(id)
            .map { process -> ResponseEntity.ok(process) }
            .orElse(ResponseEntity.notFound().build())
    }

    override fun deleteAll() {
        repositoryProcesses.deleteAll()
    }

    override fun deleteById(id: UUID): ResponseEntity<String> {
        return repositoryProcesses.findById(id)
            .map { process->
                repositoryProcesses.delete(process)
                ResponseEntity.ok("Удален процесс с идентификатором: " + process.id.toString())
            }
            .orElse(ResponseEntity.notFound().build())
    }

}