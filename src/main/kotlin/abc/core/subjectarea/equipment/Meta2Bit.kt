package abc.core.subjectarea.equipment
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class Meta2Bit : Meta2Register{
    var oid:Long=0
    var valid:Boolean=false
    var bitNum:Int=0
    var control:Boolean=false
    var dataRegNum:Int=0
    var settingRegNum:Int=0
    var bitType:Int=0
    var invert:Boolean=false
    var bitRegNum:Int=0
    var remoteEnable:Boolean=false
    var prior:Int=0
    var DOType:String=""
    var out61850Model:Boolean=false
    var in60870Model:Boolean=false
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
