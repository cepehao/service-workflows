package ru.psu.eat.servicemodeling.services

import org.springframework.stereotype.Service
import org.w3c.dom.Node
import ru.psu.eat.servicemodeling.model.*
import java.io.File
import java.util.UUID
import javax.xml.parsers.DocumentBuilderFactory


@Service
class CServiceBPMN : IServiceBPMN
{
    fun getEvent(curNode: Node, eventType: EEventType): CEvent {
        val nodeList = curNode.childNodes

        val incoming = arrayListOf<CProcessItem>()
        val outgoing = arrayListOf<CProcessItem>()

        for (i:Int in 0 until nodeList.length) {
            if (nodeList.item(i).nodeType != Node.ELEMENT_NODE) continue

            if (nodeList.item(i).nodeName == "semantic:incoming") {
                //TODO Забирать объекты из карты
                //incoming.add(CIncoming(nodeList.item(i).textContent.substring(3)))
            }

            if (nodeList.item(i).nodeName == "semantic:outgoing") {
                //TODO Забирать объекты из карты
                //outgoing.add(COutgoing(nodeList.item(i).textContent.substring(3)))
            }
        }

        return CEvent(curNode.attributes.getNamedItem("name").nodeValue, incoming, outgoing, eventType)
    }

    fun getGateway(curNode: Node, gatewayType: EGatewayType): CGateway { // todo правило выбора
        val nodeList = curNode.childNodes

        val incoming = arrayListOf<CProcessItem>()
        val outgoing = arrayListOf<CProcessItem>()

        for (i:Int in 0 until nodeList.length) {
            if (nodeList.item(i).nodeType != Node.ELEMENT_NODE) continue

            if (nodeList.item(i).nodeName == "semantic:incoming") {
                //TODO Забирать объекты из карты
                //incoming.add(CIncoming(nodeList.item(i).textContent.substring(3)))
            }

            if (nodeList.item(i).nodeName == "semantic:outgoing") {
                //TODO Забирать объекты из карты
                //outgoing.add(COutgoing(nodeList.item(i).textContent.substring(3)))
            }
        }

        return CGateway(curNode.attributes.getNamedItem("name").nodeValue, incoming, outgoing, gatewayType)
    }

    fun getTask(curNode: Node, process: CProcess): CTask {
        val nodeList = curNode.childNodes

        val incoming = arrayListOf<CProcessItem>()
        val outgoing = arrayListOf<CProcessItem>()

        for (i:Int in 0 until nodeList.length) {
            if (nodeList.item(i).nodeType != Node.ELEMENT_NODE) continue

            if (nodeList.item(i).nodeName == "semantic:incoming") {
                //TODO Забирать объекты из карты
                //incoming.add(CIncoming(nodeList.item(i).textContent.substring(3)))
            }

            if (nodeList.item(i).nodeName == "semantic:outgoing") {
                //TODO Забирать объекты из карты
                //outgoing.add(COutgoing(nodeList.item(i).textContent.substring(3)))
            }
        }

        return CTask(curNode.attributes.getNamedItem("name").nodeValue, incoming, outgoing, process)
    }

    override fun parseBPMN(): CProcess? {
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

            for (i:Int in 0 until processChildList.length) {
                processChild = processChildList.item(i)

                if (processChild == null || processChild.nodeType != Node.ELEMENT_NODE) continue

                when(processChild.nodeName) {
                    "semantic:startEvent" -> {
                        process.addEvent(processChild.attributes.getNamedItem("id").nodeValue.substring(3),
                            getEvent(processChild, EEventType.START))
                    }

                    "semantic:task" -> {
                        process.addTask(processChild.attributes.getNamedItem("id").nodeValue.substring(3),
                            getTask(processChild, process))
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
                            getGateway(processChild, EGatewayType.EXCLUSIVE))
                    }
                }
            }
        }catch(e: Exception) {
            println("Error: " + e.message)
        }

        return process
    }
}