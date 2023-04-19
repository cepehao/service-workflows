package ru.psu.workflow.serviceworkflows.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import ru.psu.workflow.serviceworkflows.services.IServiceTasks
import java.util.*

@RestController
@RequestMapping("tasks")
class CControllerTasks
(
    val serviceTasks: IServiceTasks
)
{
    @PostMapping("/source")
    fun addPythonScript(
        @RequestParam("task_id") taskId: String,
        @RequestParam("python_script") pythonScript: MultipartFile
    )
    {
        serviceTasks.addPythonScript(taskId, pythonScript)
    }

    @GetMapping("/source")
    fun getPythonScrit(
        @RequestParam("task_id") taskId: String
    ): String
    {
        return serviceTasks.getPythonScript(taskId)
    }
}