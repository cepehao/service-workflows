package ru.psu.eat.servicemodelling.services

import org.springframework.stereotype.Service
import ru.psu.eat.servicemodelling.model.CProcess
import ru.psu.eat.servicemodelling.repositories.IRepositoryProcesses

@Service
class CServiceProcesses(val repositoryProcess: IRepositoryProcesses): IServiceProcesses {
    override fun save(item: CProcess) {
        return repositoryProcess.save(item)
    }
}