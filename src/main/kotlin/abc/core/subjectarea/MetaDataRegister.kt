package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class MetaDataRegister{
    var oid:Long=0
    var valid:Boolean=false
    var ESSNode:EntityLink<ESSNode> = EntityLink<ESSNode>()
    var stateMask:Int=0
    var unit:String=""
    var power:Int=0
    var step:Int=0
    var count:Int=0
    var count2:Int=0
    var count3:Int=0
    var streamType:Int=0
    var regNum:Int=0
    var format:Int=0
    var type:Int=0
    var subSystem:EntityLink<MetaSubSystem> = EntityLink<MetaSubSystem>()
    var MetaExternalSystem:EntityLink<MetaExternalSystem> = EntityLink<MetaExternalSystem>()
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
