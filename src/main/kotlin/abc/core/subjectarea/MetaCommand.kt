package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class MetaCommand{
    var oid:Long=0
    var valid:Boolean=false
    var MetaCommandRegister:EntityLink<MetaCommandRegister> = EntityLink<MetaCommandRegister>()
    var regNum:Int=0
    var code:Int=0
    var stateMask:Int=0
    var remoteEnable:Boolean=false
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
