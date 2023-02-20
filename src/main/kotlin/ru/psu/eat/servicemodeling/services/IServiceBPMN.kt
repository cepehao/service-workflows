package ru.psu.eat.servicemodeling.services

import ru.psu.eat.servicemodeling.model.CProcess

interface IServiceBPMN {
    fun parseBPMN(): CProcess?
}