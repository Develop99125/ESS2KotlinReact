package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class MetaCommandRegister{
    var oid:Long=0
    var valid:Boolean=false
    var commands:ArrayList<MetaCommand> = ArrayList<MetaCommand>()
    var command:Boolean=false
    var count:Int=0
    var step:Int=0
    var dEvent:Boolean=false
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
