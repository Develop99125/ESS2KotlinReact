package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink
import abc.core.subjectarea.equipment.Meta2Array
import abc.core.subjectarea.equipment.Meta2Equipment

@Serializable
class ESS2Equipment{
    var oid:Long=0
    var valid:Boolean=false
    var ESS2Architecture:EntityLink<ESS2Architecture> = EntityLink<ESS2Architecture>()
    var multiUnit:Boolean=false
    var logUnits:ArrayList<ESS2LogUnit> = ArrayList<ESS2LogUnit>()
    var metaFile:EntityLink<ESS2MetaFile> = EntityLink<ESS2MetaFile>()
    var shortName:String=""
    var title:String=""
    var comment:String=""
    var data = Meta2Equipment()

    fun generateHighLinks(array: Meta2Array){
        for (elem1 in array.elem.list!!.getArrayOfArraysWithRegisters()){
            for(elem in elem1){
                elem.high = array
            }
        }

        for (elem in array.elem.list!!.arrays){
            elem.high = array
            generateHighLinks(elem)
        }
    }
}
