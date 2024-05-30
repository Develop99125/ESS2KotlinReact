package abc.core.subjectarea.equipment
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class Meta2BitArray : Meta2Register{
    var oid:Long=0
    var valid:Boolean=false
    var elem:Meta2Face=Meta2Face()
    var size:Int=0
    var step:Int=0
    var ownBase:Int=0
    var DOType:String=""
    var out61850Model:Boolean=false
    var in60870Model:Boolean=false
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
