package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESS2MetaFile{
    var oid:Long=0
    var valid:Boolean=false
    var file:EntityLink<Artifact> = EntityLink<Artifact>()
    var metaType:Int=0
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
