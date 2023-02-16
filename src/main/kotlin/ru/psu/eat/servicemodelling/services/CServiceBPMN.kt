package ru.psu.eat.servicemodelling.services

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import ru.psu.eat.servicemodelling.model.CIncoming
import ru.psu.eat.servicemodelling.model.COutgoing
import ru.psu.eat.servicemodelling.model.CProcess
import ru.psu.eat.servicemodelling.model.CTask
import java.util.UUID

@Configuration
@ComponentScan("ru.psu.eat.servicemodelling")
class CServiceBPMN {
    @Bean
    @Primary
    fun getCProcess(): CProcess? {
        var process = CProcess("test", UUID.randomUUID())
        val inc = arrayListOf<CIncoming>(CIncoming("id-inc-1"), CIncoming("id-inc-2"), CIncoming("id-inc-3"))
        val out = arrayListOf<COutgoing>(COutgoing("id-out-1"), COutgoing("id-out-2"))
        process.addTask("id-task-1", CTask("task1", inc, out))
        return process
    }
}