package ru.psu.eat.servicemodelling.controllers

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.psu.eat.servicemodelling.model.CProcess
import ru.psu.eat.servicemodelling.services.IServiceProcesses

@RestController
@RequestMapping
class CControllerProcesses (val serviceProcesses : IServiceProcesses) {

    @PostMapping
    fun save(@RequestBody item: CProcess){
        serviceProcesses.save(item)
    }

}