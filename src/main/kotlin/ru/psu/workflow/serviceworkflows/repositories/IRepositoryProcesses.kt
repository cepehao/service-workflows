package ru.psu.workflow.serviceworkflows.repositories

import org.springframework.stereotype.Repository
import ru.psu.workflow.serviceworkflows.model.CProcess
import org.springframework.data.repository.CrudRepository
import java.util.*

@Repository
interface IRepositoryProcesses: CrudRepository<CProcess, UUID> // только метод save

