package ru.psu.eat.servicemodelling.repositories

import org.springframework.stereotype.Repository
import ru.psu.eat.servicemodelling.model.CProcess
import org.springframework.data.repository.CrudRepository
import java.util.*

@Repository
interface IRepositoryProcesses: CrudRepository<CProcess, UUID> // только метод save

