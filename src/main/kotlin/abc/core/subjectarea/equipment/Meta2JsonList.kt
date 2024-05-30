package abc.core.subjectarea.equipment

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class Meta2JsonList {
    var comments: ArrayList<Meta2Comment> = ArrayList()
    var settingRegisters: ArrayList<Meta2SettingRegister> = ArrayList()
    var strings: ArrayList<Meta2String> = ArrayList()
    var stateRegisters: ArrayList<Meta2StateRegister> = ArrayList()
    var bitRegisters: ArrayList<Meta2BitRegister> = ArrayList()
    var dataRegisters: ArrayList<Meta2DataRegister> = ArrayList()
    var dateTimes: ArrayList<Meta2DateTime> = ArrayList()
    var arrays: ArrayList<Meta2Array> = ArrayList()
    var collections: ArrayList<Meta2Collection> = ArrayList()
    constructor() {}

    fun getArrayOfArraysWithRegisters() : ArrayList<ArrayList<out Meta2Register>>{
        return arrayListOf(comments, settingRegisters, strings, stateRegisters, bitRegisters,
            dataRegisters, dateTimes)
    }

    fun getRegisterValue(numOfReg: Int) : Meta2Register{
        val outArrays = getArrayOfArraysWithRegisters()

        for (outArray in outArrays){
            for(unit in outArray){
                if (unit.regNum == numOfReg){
                    return unit
                }
            }
        }

        for(unit1 in arrays){
            val reg = getRegisterValueInArray(numOfReg, unit1)
            if (reg != null) return reg
        }

        for(unit1 in collections){
            val reg = getRegisterValueInCollection(numOfReg, unit1)
            if (reg != null) return reg
        }

        return Meta2Register(-2, -1)
    }

    private fun getRegisterValueInArray(numOfReg: Int, array: Meta2Array): Meta2Register?{
        val outArrays = array.elem.list!!.getArrayOfArraysWithRegisters()

        for (outArray in outArrays){
            for(unit in outArray){
                if (unit.regNum == numOfReg){
                    return unit
                }
            }
        }

        for(element in array.elem.list!!.arrays){
            val reg = getRegisterValueInArray(numOfReg, element)
            if (reg != null) return reg
        }

        for (element in array.elem.list!!.collections){
            val reg = getRegisterValueInCollection(numOfReg, element)
            if (reg != null) return reg
        }

        return null
    }

    private fun getRegisterValueInCollection(numOfReg: Int, collection: Meta2Collection): Meta2Register?{
        val outArrays = collection.list!!.getArrayOfArraysWithRegisters()
        for (outArray in outArrays){
            for(unit in outArray){
                if (unit.regNum == numOfReg){
                    return unit
                }
            }
        }

        for(element in collection.list!!.arrays){
            val reg = getRegisterValueInArray(numOfReg, element)
            if (reg != null) return reg
        }


        for (element in collection.list!!.collections){
            val reg = getRegisterValueInCollection(numOfReg, element)
            if (reg != null) return reg
        }

        return null
    }
}