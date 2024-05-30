import abc.core.subjectarea.ESS2Architecture
import abc.core.subjectarea.ESS2Device
import abc.core.subjectarea.ESS2Equipment
import abc.core.subjectarea.equipment.Meta2Array
import abc.core.subjectarea.equipment.Meta2Register
import abc.core.subjectarea.view.*
import react.Props
import react.State

class RenderingWithRegisters(deployed: ESS2Architecture, form: Meta2GUIForm) {
    var deployed : ESS2Architecture = deployed
    var form : Meta2GUIForm = form

    fun putOneLinkRegister(element: RComponent<Props, State>, link: Meta2RegLink, offset: Int){
        val regNumFull = link.regNum + offset
        val regSize = link.register.size16Bit()
        for(i in 0 until regSize){
            element.device.putValue(element.devUnit, regNumFull + i, 0)
        }
    }

    suspend fun puttingRegisters(){
        //val guiList = form.controls.list!!.getArrayOfArrays()
        for(elem in guiList){
            //for(elem in elem1){
                val link = elem.regNum
                if (link.regNum == -2) continue
                putOneLinkRegister(elem, link, elem.regOffset)
                // SettingLinks + DataLinks

                //-------------------------
            //}
        }
        for(device in deployed.devices){
            val list2 = device.createList(false)
            for(list in list2){
                //println("${device.shortName} [${list.unitIdx}] = ${list.data.size}")
                val values = askRegisters(device, list)
                repaintValuesOnAnswer(device, list.unitIdx, values, form)
            }
        }
    }

    fun getRegisterData(device: ESS2Device, link: Meta2RegLink, unitIdx: Int, offset: Int) : ArrayList<Int>? {
        val regNumFull = link.regNum + offset
        val regSize = link.register.size16Bit()
        val data = ArrayList<Int>()
        var good = true
        for(i in 0 until regSize){
            val vv = device.getValue(unitIdx, regNumFull + i)
            if (vv == null){
                println("Не найден регистр в ответе сервера ${regNumFull + i}")
                good = false
                break
            }
            data.add(vv)
        }
        return if(good) data else null
    }

    fun repaintValuesOnAnswer(device: ESS2Device, unitIdx: Int, values: ArrayList<Int>, form: Meta2GUIForm){
        device.clearCash(unitIdx)
        for(i in values.indices step 2){
            device.putValue(unitIdx, values[i], values[i+1])
        }
        //val guiList = form.controls.list!!.getArrayOfArrays()
        for(element in guiList){
            //for(element in elem){
                val link = element.regNum
                if (link.regNum == -2) continue
                if (element.device.shortName != device.shortName) continue
                if (element.devUnit != unitIdx) continue
                val data = getRegisterData(device, link, unitIdx, element.regOffset)
                if (data == null) continue
                element.putValue(data)
           //}
        }
    }

    fun mappingRegisters(meta: RComponent<Props, State>, baseX: Int, baseY: Int, groupLevel: Int,
                         groupIndexes: ArrayList<Int>) {
        if (meta is Meta2GUIArray) {
            val array = meta as Meta2GUIArray
            val elem = array.elem
            for (i in 0 until array.size) {
                groupIndexes[groupLevel] = i
                val xx = baseX + (if (array.dxy < 0) -array.dxy * i else 0)
                val yy = baseY + (if (array.dxy > 0) array.dxy * i else 0)
                if (elem != null) {
                    mappingRegisters(elem, xx, yy, groupLevel + 1, groupIndexes)
                }
            }
        } else if (meta is Meta2GUICollection) {
            val collection = meta as Meta2GUICollection
            if (collection.list != null) {
                for (elem1 in collection.list!!.getArrayOfArrays()) {
                    for (elem in elem1) {
                        mappingRegisters(elem, baseX, baseY, groupLevel, groupIndexes)
                    }
                }

                for (elem in collection.list!!.guiArrays) {
                    mappingRegisters(elem, baseX, baseY, groupLevel, groupIndexes)
                }
            }
        } else {
            //if (meta !is Meta2GUI2StateBox) return
            val newElem = meta.clone()
            newElem.dxOffset = baseX
            newElem.dyOffset = baseY
            newElem.groupLevel = groupLevel
            newElem.regNum = meta.regNum
            //println(newElem.regNum.regNum)
            //if(meta is Meta2GUI2StateBox) println("${meta.dxOffset} ${meta.dyOffset}")

            if (newElem.regNum.regNum != -2) {
                val link = newElem.regNum
                val register = link.register
                val equipName = link.equipName

                var equipment: ESS2Equipment? = null
                for (elem in deployed.equipments) {
                    if (elem.shortName == equipName) {
                        equipment = elem
                        break
                    }
                }

                if (equipment == null) {
                    println("Не найдено оборудование: $equipName")
                    return
                }

                val connectorSize = equipment.logUnits.size
                if (connectorSize == 0) {
                    println("Нет устройств для: $equipName")
                    return
                }

                val treeLevel = register.getArrayLevel() - 1
                var grlevel = groupLevel - 1
                val stackLevel = form.level - 1
                if (!link.ownUnit && treeLevel > stackLevel + 1) {
                    println("Уровень массива мета-данных больше уровня формы")
                    return
                }

                var regOffset = 0
                var regLevel = treeLevel

                if (link.ownUnit) {
                    newElem.regOffset = 0
                    newElem.unitIdx = link.unitIdx
                } else {
                    var cc: Meta2Register? = register.high
                    while (cc != null) {
                        if (cc !is Meta2Array) {
                            cc = cc.high
                            continue
                        }
                        val array = cc as Meta2Array
                        var elemIdx = 0
                        elemIdx += if (grlevel < 0) 0 else groupIndexes[grlevel]
                        if (elemIdx >= array.size) return
                        when (array.type) {
                            0 -> {
                                regOffset += array.step * elemIdx
                            }
                            1 -> {
                                if (!newElem.regNum.ownUnit) {
                                    newElem.unitIdx = elemIdx
                                }
                            }
                        }
                        grlevel--
                        regLevel--

                        cc = cc.high
                    }
                    newElem.regOffset = regOffset
                }

                if (newElem.unitIdx >= connectorSize) {
                    println("Индекс Unut ${meta.unitIdx} превышен для $equipName")
                    return
                }

                val unit = equipment.logUnits[newElem.unitIdx]
                //val device = deployed.getDeviceById(unit.device.oid)
                val device = deployed.getDeviceById(13)
                if (device == null) {
                    println("Такого device нет")
                    return
                }

                newElem.device = device
                newElem.devUnit = unit.unit
            }
            guiList.add(newElem)
        }
    }
}

