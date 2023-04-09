package ru.psu.workflow.serviceworkflows.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.psu.workflow.serviceworkflows.model.CProcess
import ru.psu.workflow.serviceworkflows.services.IServiceBPMN
import ru.psu.workflow.serviceworkflows.services.IServiceProcesses
import java.util.UUID

@RestController
@RequestMapping("processes")
class CControllerProcesses
(
    val serviceProcesses: IServiceProcesses,
    val serviceBPMN: IServiceBPMN
)
{

    @PostMapping("/upload_bpmn", params = ["id"])
    fun uploadBPMN(@RequestParam id: UUID): CProcess {
        return serviceProcesses.save(serviceBPMN.parseBPMN(id))
    }

    @PostMapping("/upload_bpmn_with_body")
    fun uploadBPMN(
        @RequestParam(required  = false) id: UUID?,
        @RequestBody fileBPMN: MultipartFile
    )                                       : CProcess
    {
        return serviceProcesses.save(serviceBPMN.parseBPMN(id, fileBPMN))
    }


    @GetMapping
    fun getAll(): Iterable<CProcess> {
        return serviceProcesses.getAll()
    }

    @GetMapping (params = ["id"])
    fun getById(@RequestParam id: UUID): ResponseEntity<CProcess> {
        return serviceProcesses.getById(id)
    }

    @DeleteMapping(params = ["id"])
    fun deleteById(@RequestParam id: UUID): ResponseEntity<String> {
        return serviceProcesses.deleteById(id)
    }



}