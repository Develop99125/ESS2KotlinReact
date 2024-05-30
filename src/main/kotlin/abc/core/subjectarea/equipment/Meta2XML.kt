package abc.core.subjectarea.equipment
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class Meta2XML{
    var oid:Long=0
    var valid:Boolean=false
    var xmlType:Int=0
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
