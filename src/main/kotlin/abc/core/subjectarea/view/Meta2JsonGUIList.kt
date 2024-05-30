package abc.core.subjectarea.view

import RComponent
import abc.core.subjectarea.equipment.Meta2JsonList
import kotlinx.serialization.Serializable
import react.Props
import react.State

@Serializable
class Meta2JsonGUIList {
    var guiForms : ArrayList<Meta2GUIForm> = ArrayList()
    var gui2StateBoxs: ArrayList<Meta2GUI2StateBox> = ArrayList()
    var gui2StateBoxSmalls: ArrayList<Meta2GUI2StateBoxSmall> = ArrayList()
    var gui3StateBoxs: ArrayList<Meta2GUI3StateBox> = ArrayList()
    var guiArrays : ArrayList<Meta2GUIArray> = ArrayList()
    var guiBit2Commands: ArrayList<Meta2GUIBit2Commands> = ArrayList()
    var guiBitCheckBoxs: ArrayList<Meta2GUIBitCheckBox> = ArrayList()
    var guiBitScripts: ArrayList<Meta2GUIBitScript> = ArrayList()
    var guiBitsLists: ArrayList<Meta2GUIBitsList> = ArrayList()
    var guiBitStateCmds: ArrayList<Meta2GUIBitStateCmd> = ArrayList()
    var guiButtons: ArrayList<Meta2GUIButton> = ArrayList()
    var guiCollections: ArrayList<Meta2GUICollection> = ArrayList()
    var guiCommandBits: ArrayList<Meta2GUICommandBit> = ArrayList()
    var guiCurrentDateTimes: ArrayList<Meta2GUICurrentDateTime> = ArrayList()
    var guiDatas: ArrayList<Meta2GUIData> = ArrayList()
    var guiDataLabels: ArrayList<Meta2GUIDataLabel> = ArrayList()
    var guiDateTimes: ArrayList<Meta2GUIDateTime> = ArrayList()
    var guiEnvVars: ArrayList<Meta2GUIEnvVar> = ArrayList()
    var guiESSSettingBooleans: ArrayList<Meta2GUIESSSettingBoolean> = ArrayList()
    var guiESSSettingInts: ArrayList<Meta2GUIESSSettingInt> = ArrayList()
    var guiESSSettingStrings: ArrayList<Meta2GUIESSSettingString> = ArrayList()
    var guiFormSelectors: ArrayList<Meta2GUIFormSelector> = ArrayList()
    var guiImages: ArrayList<Meta2GUIImage> = ArrayList()
    var guiImageBits: ArrayList<Meta2GUIImageBit> = ArrayList()
    var guiImageBitCmds: ArrayList<Meta2GUIImageBitCmd> = ArrayList()
    var guiImageDataLevels: ArrayList<Meta2GUIImageDataLevel> = ArrayList()
    var guiImageScriptLevels: ArrayList<Meta2GUIImageScriptLevel> = ArrayList()
    var guiIndexLabels: ArrayList<Meta2GUIIndexLabel> = ArrayList()
    var guiLabels: ArrayList<Meta2GUILabel> = ArrayList()
    var guiLevelIndicators: ArrayList<Meta2GUILevelIndicator> = ArrayList()
    var guiLevelMultiIndicators: ArrayList<Meta2GUILevelMultiIndicator> = ArrayList()
    var guiLevelWBs: ArrayList<Meta2GUILevelWB> = ArrayList()
    var guiMultiBitStates: ArrayList<Meta2GUIMultiBitState> = ArrayList()
    var guiScripts: ArrayList<Meta2GUIScript> = ArrayList()
    var guiScriptLabels: ArrayList<Meta2GUIScriptLabel> = ArrayList()
    var guiSettings: ArrayList<Meta2GUISetting> = ArrayList()
    var guiStateSelectors: ArrayList<Meta2GUIStateSelector> = ArrayList()
    var guiStateSets: ArrayList<Meta2GUIStateSet> = ArrayList()
    var guiStrings: ArrayList<Meta2GUIString> = ArrayList()
    var guiTimeSettings: ArrayList<Meta2GUITimeSetting> = ArrayList()
    var guiTwoBytess: ArrayList<Meta2GUITwoBytes> = ArrayList()

    fun getArrayOfArrays() : ArrayList<ArrayList<out RComponent<Props, State>>> {
        return arrayListOf(gui2StateBoxs, gui2StateBoxSmalls, gui3StateBoxs, guiBit2Commands,
            guiBitCheckBoxs, guiBitScripts, guiBitsLists, guiBitStateCmds, guiButtons, guiCollections,
            guiCommandBits, guiCurrentDateTimes, guiDatas, guiDataLabels, guiDateTimes, guiEnvVars,
            guiESSSettingBooleans, guiESSSettingStrings, guiESSSettingInts, guiFormSelectors,
            guiImages, guiImageBits, guiImageBitCmds, guiImageDataLevels, guiImageScriptLevels, guiIndexLabels,
            guiLabels, guiLevelIndicators, guiLevelMultiIndicators, guiLevelWBs, guiMultiBitStates,
            guiScripts, guiScriptLabels, guiSettings, guiStateSelectors, guiStateSets, guiStrings,
            guiTimeSettings, guiTwoBytess
            )
    }

    fun fillValuesOfRegisters(equipmentData: Meta2JsonList?) {
        val outArrays = getArrayOfArrays()
        for(outArray in outArrays){
            for(unit in outArray){
                replaceValues(equipmentData, unit)
            }
        }

        for(element in guiArrays){
            fillValuesInArrays(equipmentData, element)
        }
    }

    private fun fillValuesInArrays(equipmentData: Meta2JsonList?, array: Meta2GUIArray){
        for(outArray in array.elem?.list!!.getArrayOfArrays()){
            for(unit in outArray){
                replaceValues(equipmentData, unit)
            }
        }

        for(element in array.elem?.list!!.guiArrays){
            fillValuesInArrays(equipmentData, element)
        }
    }

    private fun replaceValues(equipmentData: Meta2JsonList?, unit : RComponent<Props, State>){
        if(unit.regNum.regNum != -2) {
            val returnedRegister = equipmentData!!.getRegisterValue(unit.regNum.regNum)
            unit.regNum.register = returnedRegister

            if (unit is Meta2GUILevelIndicator || unit is Meta2GUILevelMultiIndicator) {
                setValue(equipmentData, unit.maxFail)
                setValue(equipmentData, unit.maxWarn)
                setValue(equipmentData, unit.minWarn)
                setValue(equipmentData, unit.minFail)
            }
        }
    }

    private fun setValue(equipmentData: Meta2JsonList?, item: Meta2RegLink){
        if (item.regNum != -2) {
            val returnedRegister = equipmentData!!.getRegisterValue(item.regNum)
            item.register = returnedRegister
        }
    }
}