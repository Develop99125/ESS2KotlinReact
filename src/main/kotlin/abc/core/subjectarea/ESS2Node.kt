package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESS2Node{
    var oid:Long=0
    var valid:Boolean=false
    var architecture:EntityLink<ESS2Architecture> = EntityLink<ESS2Architecture>()
    var ESSServerIP:String=""
    var ESSServerPort:Int=0
    var enabled:Boolean=false
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
