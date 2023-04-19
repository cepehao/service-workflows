package ru.psu.workflow.serviceworkflows.services

import org.springframework.web.multipart.MultipartFile

interface IServiceTasks
{
    fun addPythonScript(taskId: String, pythonScript: MultipartFile)

    fun getPythonScript(taskId: String): String
}