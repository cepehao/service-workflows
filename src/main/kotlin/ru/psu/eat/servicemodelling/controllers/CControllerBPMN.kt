package ru.psu.eat.servicemodelling.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.psu.eat.servicemodelling.model.CProcess

@RestController
@RequestMapping("/upload_bpmn")
class CControllerBPMN {
    @Autowired
    val process: CProcess? = null

    @GetMapping
    fun parseBPMN(): CProcess? {
        return process
    }
}