package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class PLMConfigValue{
    var oid:Long=0
    var valid:Boolean=false
    var PLMConfig:EntityLink<PLMConfig> = EntityLink<PLMConfig>()
    var regNum:Int=0
    var regValue:Int=0
    constructor() {}
}
