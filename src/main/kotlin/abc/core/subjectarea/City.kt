package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class City{
    var oid:Long=0
    var valid:Boolean=false
    var name:String=""
    var type:Int=0
    constructor() {}
}
