package ru.psu.eat.servicemodeling.services

import org.springframework.stereotype.Service
import ru.psu.eat.servicemodeling.model.CProcess
import ru.psu.eat.servicemodeling.repositories.IRepositoryProcesses

@Service
class CServiceProcesses(val repositoryProcess: IRepositoryProcesses): IServiceProcesses {
    override fun save(item: CProcess) : CProcess
    {
        return repositoryProcess.save(item)
    }
}