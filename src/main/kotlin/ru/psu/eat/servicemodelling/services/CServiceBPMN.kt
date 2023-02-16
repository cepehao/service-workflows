package ru.psu.eat.servicemodelling.services

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.w3c.dom.Node
import ru.psu.eat.servicemodelling.model.*
import java.io.File
import java.util.UUID
import javax.xml.parsers.DocumentBuilderFactory

fun getEvent(curNode: Node, eventType: EEventType): CEvent {
    val nodeList = curNode.childNodes

    val incomingList = arrayListOf<CIncoming>()
    val outgoingList = arrayListOf<COutgoing>()

    for (i:Int in 0..nodeList.length - 1) {
        if (nodeList.item(i).nodeType != Node.ELEMENT_NODE) continue

        if (nodeList.item(i).nodeName == "semantic:incoming") {
            incomingList.add(CIncoming(nodeList.item(i).textContent.substring(3)))
        }

        if (nodeList.item(i).nodeName == "semantic:outgoing") {
            outgoingList.add(COutgoing(nodeList.item(i).textContent.substring(3)))
        }
    }

    return CEvent(curNode.attributes.getNamedItem("name").nodeValue, incomingList, outgoingList, eventType)
}

fun getGateway(curNode: Node, gatewayType: EGateway): CGateway { // todo правило выбора
    val nodeList = curNode.childNodes

    val incomingList = arrayListOf<CIncoming>()
    val outgoingList = arrayListOf<COutgoing>()

    for (i:Int in 0..nodeList.length - 1) {
        if (nodeList.item(i).nodeType != Node.ELEMENT_NODE) continue

        if (nodeList.item(i).nodeName == "semantic:incoming") {
            incomingList.add(CIncoming(nodeList.item(i).textContent.substring(3)))
        }

        if (nodeList.item(i).nodeName == "semantic:outgoing") {
            outgoingList.add(COutgoing(nodeList.item(i).textContent.substring(3)))
        }
    }

    return CGateway(curNode.attributes.getNamedItem("name").nodeValue, incomingList, outgoingList, gatewayType)
}

fun getTask(curNode: Node): CTask {
    val nodeList = curNode.childNodes

    val incomingList = arrayListOf<CIncoming>()
    val outgoingList = arrayListOf<COutgoing>()

    for (i:Int in 0..nodeList.length - 1) {
        if (nodeList.item(i).nodeType != Node.ELEMENT_NODE) continue

        if (nodeList.item(i).nodeName == "semantic:incoming") {
            incomingList.add(CIncoming(nodeList.item(i).textContent.substring(3)))
        }

        if (nodeList.item(i).nodeName == "semantic:outgoing") {
            outgoingList.add(COutgoing(nodeList.item(i).textContent.substring(3)))
        }
    }

    return CTask(curNode.attributes.getNamedItem("name").nodeValue, incomingList, outgoingList)
}

@Configuration
@ComponentScan("ru.psu.eat.servicemodelling")
class CServiceBPMN {
    @Bean
    @Primary
    fun parseBPMN(): CProcess? {
        val uuid = UUID.randomUUID()
        val file = File("C:\\Users\\cepeh\\OneDrive\\Рабочий стол\\sel.bpmn")

        var process: CProcess? = null

        try {
            val dbf = DocumentBuilderFactory.newInstance()
            val doc = dbf.newDocumentBuilder().parse(file)

            val processNode = doc.getElementsByTagName("semantic:process").item(0)

            process = CProcess(processNode.attributes.getNamedItem("name").nodeValue, uuid)

            val processChildList = processNode.childNodes
            var processChild: Node

            for (i:Int in 0..processChildList.length - 1) {
                processChild = processChildList.item(i)

                if (processChild == null || processChild.nodeType != Node.ELEMENT_NODE) continue

                when(processChild.nodeName) {
                    "semantic:startEvent" -> {
                        process.addEvent(processChild.attributes.getNamedItem("id").nodeValue.substring(3),
                            getEvent(processChild, EEventType.START))
                    }

                    "semantic:task" -> {
                        process.addTask(processChild.attributes.getNamedItem("id").nodeValue.substring(3),
                            getTask(processChild))
                    }

                    "semantic:endEvent" -> {
                        process.addEvent(processChild.attributes.getNamedItem("id").nodeValue.substring(3),
                            getEvent(processChild, EEventType.END))
                    }

                    "semantic:intermediateCatchEvent" -> {
                        process.addEvent(processChild.attributes.getNamedItem("id").nodeValue.substring(3),
                            getEvent(processChild, EEventType.INTERMEDIATE))
                    }

                    "semantic:exclusiveGateway" -> {
                        process.addGateway(processChild.attributes.getNamedItem("id").nodeValue.substring(3),
                            getGateway(processChild, EGateway.EXCLUSIVE))
                    }
                }
            }
        }catch(e: Exception) {
            println("Error: " + e.message)
        }

        return process
    }
}