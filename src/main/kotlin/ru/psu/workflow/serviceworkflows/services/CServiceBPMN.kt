package ru.psu.workflow.serviceworkflows.services

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import ru.psu.workflow.serviceworkflows.model.*
import java.io.File
import java.util.UUID
import javax.xml.parsers.DocumentBuilderFactory


@Service
class CServiceBPMN : IServiceBPMN
{
    // собираем элемент процесса - событие, связи пока храним строковыми идентификаторами
    fun createEvent(id: String, curNode: Node, eventType: EEventType): CEvent {
        val nodeList = curNode.childNodes
        var node: Node

        val incomingIdList = arrayListOf<String>()
        val outgoingIdList = arrayListOf<String>()

        for (i:Int in 0 until nodeList.length) {
            node = nodeList.item(i)

            if (node.nodeType != Node.ELEMENT_NODE) continue

            if (node.nodeName == "semantic:incoming") {
                incomingIdList.add(node.textContent.substring(3))
            }

            if (node.nodeName == "semantic:outgoing") {
                outgoingIdList.add(node.textContent.substring(3))
            }
        }

        return CEvent(id, curNode.attributes.getNamedItem("name").nodeValue, incomingIdList, outgoingIdList, eventType)
    }

    fun createGateway(id: String, curNode: Node, gatewayType: EGatewayType): CGateway { // todo правило выбора
        val nodeList = curNode.childNodes
        var node: Node

        val incomingIdList = arrayListOf<String>()
        val outgoingIdList = arrayListOf<String>()

        for (i:Int in 0 until nodeList.length) {
            node = nodeList.item(i)

            if (node.nodeType != Node.ELEMENT_NODE) continue

            if (node.nodeName == "semantic:incoming") {
                incomingIdList.add(node.textContent.substring(3))
            }

            if (node.nodeName == "semantic:outgoing") {
                outgoingIdList.add(node.textContent.substring(3))
            }
        }

        return CGateway(id, curNode.attributes.getNamedItem("name").nodeValue, incomingIdList, outgoingIdList, gatewayType)
    }

    fun createTask(id: String, curNode: Node): CTask {
        val nodeList = curNode.childNodes
        var node: Node

        val incomingIdList = arrayListOf<String>()
        val outgoingIdList = arrayListOf<String>()


        for (i:Int in 0 until nodeList.length) {
            node = nodeList.item(i)

            if (node.nodeType != Node.ELEMENT_NODE) continue

            if (node.nodeName == "semantic:incoming") {
                incomingIdList.add(node.textContent.substring(3))
            }

            if (node.nodeName == "semantic:outgoing") {
                outgoingIdList.add(node.textContent.substring(3))
            }
        }

        return CTask(id, curNode.attributes.getNamedItem("name").nodeValue, incomingIdList, outgoingIdList)
    }

    // создаем карту всех процессов, вместе со строковыми идентификаторами связей, без ссылок на другие элементы процесса
    fun getProcessObjectsMap(nodeList: NodeList, curProcess: CProcess): MutableMap<String, CProcessItem> {
        var node: Node
        var id: String
        val processObjectsMap = mutableMapOf<String, CProcessItem>()

        for (i:Int in 0 until nodeList.length) {
            node = nodeList.item(i)

            if (node.nodeType != Node.ELEMENT_NODE) continue

            id = node.attributes.getNamedItem("id").nodeValue.substring(3)

            when(node.nodeName) {
                "semantic:startEvent" -> {
                    processObjectsMap[id] = createEvent(id, node, EEventType.START)
                }

                "semantic:task" -> {
                    processObjectsMap[id] = createTask(id, node)
                }

                "semantic:endEvent" -> {
                    processObjectsMap[id] = createEvent(id, node, EEventType.END)
                }

                "semantic:intermediateCatchEvent" -> {
                    processObjectsMap[id] = createEvent(id, node, EEventType.INTERMEDIATE)
                }

                "semantic:exclusiveGateway" -> {
                    processObjectsMap[id] = createGateway(id, node, EGatewayType.EXCLUSIVE)
                }
            }
            processObjectsMap[id]?.process = curProcess
        }

        return processObjectsMap
    }

    //проверить список строковых идентификаторов на входы/выходы в другие элементы процесса, вернуть список элементов процесса
    fun createIncomingItemList (
        items: Map<String, CProcessItem>,
        incomingIdList: List<String>,
        incomingList : MutableList<CProcessItem>
    ) {
        for (id in incomingIdList) {
            for (item in items.values) {
                if (item.checkOutgoing(id)) incomingList.add(item)
            }
        }
    }

    fun createOutgoingItemList (
        items: Map<String, CProcessItem>,
        outgoingIdList: List<String>,
        outgoingList: MutableList<CProcessItem>
    ) {
        for (id in outgoingIdList) {
            for (item in items.values) {
                if (item.checkIncoming(id)) outgoingList.add(item)
            }
        }
    }

    // добавляем в карту элементов процесса связи ссылками на объекты
    fun addConnections(items: MutableMap<String, CProcessItem>) {

        var incomingIdList: ArrayList<String>
        var outgoingIdList: ArrayList<String>

        for (item in items.values) {
            incomingIdList = item.getIncomingIdList() // получили список строковых идентификаторов
            outgoingIdList = item.getOutgoingIdList() // связей из i-ого объекта

            createIncomingItemList(items, incomingIdList, item.incomingItems) // теперь определим на какие именно
            createOutgoingItemList(items, outgoingIdList, item.outgoingItems) // элементы процесса связывают эти идентификаторы
        }

    }

    // собираем из карты элементов процесса итоговый процесс целиком
    fun makeProcess(process: CProcess, processItemMap: MutableMap<String, CProcessItem>) {
        for (entry in processItemMap.entries.iterator()) {
            when (entry.value) {
                is CEvent -> process.processItems.add(entry.value as CEvent)

                is CTask -> process.processItems.add(entry.value as CTask)

                is CGateway -> process.processItems.add(entry.value as CGateway)
            }
        }
    }

    override fun parseBPMN(id: UUID?, fileBPMN: MultipartFile): CProcess {
        var process: CProcess? = null

        try {
            val dbf = DocumentBuilderFactory.newInstance()
            val doc = dbf.newDocumentBuilder().parse(fileBPMN.inputStream)

            val processNode = doc.getElementsByTagName("semantic:process").item(0)

            if (id != null) {
                process = CProcess(id, processNode.attributes.getNamedItem("name").nodeValue)
            }else {
                process = CProcess(UUID.randomUUID(), processNode.attributes.getNamedItem("name").nodeValue)
            }

            val processChildList = processNode.childNodes

            val processItemMap = getProcessObjectsMap(processChildList, process)

            addConnections(processItemMap)

            makeProcess(process, processItemMap)

        }catch(e: Exception) {
            println("Error: " + e.message)
        }

        return process!!
    }




}