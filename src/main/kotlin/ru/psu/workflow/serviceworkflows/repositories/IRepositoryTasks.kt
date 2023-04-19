package ru.psu.workflow.serviceworkflows.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.psu.workflow.serviceworkflows.model.CTask

@Repository
interface IRepositoryTasks: CrudRepository<CTask, String>
{

}