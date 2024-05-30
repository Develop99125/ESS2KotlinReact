package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class MetaFileModule{
    var oid:Long=0
    var valid:Boolean=false
    var module:String=""
    var path:String=""
    var MetaExternalSystem:EntityLink<MetaExternalSystem> = EntityLink<MetaExternalSystem>()
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
