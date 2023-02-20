package ru.psu.eat.servicemodeling.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.psu.eat.servicemodeling.model.CProcess
import ru.psu.eat.servicemodeling.services.IServiceBPMN
import ru.psu.eat.servicemodeling.services.IServiceProcesses

@RestController
@RequestMapping("/bpmn")
class CControllerBPMN
/********************************************************************************************************
 * Конструктор.                                                                                         *
 * @param serviceBPMN - сервис с логикой для работы со списком процессов.                               *
 *******************************************************************************************************/
(
    val serviceBPMN: IServiceBPMN
)
{
    @PostMapping(path=["/upload"])
    fun parseBPMN(): CProcess? {
        return serviceBPMN.parseBPMN()
    }
}