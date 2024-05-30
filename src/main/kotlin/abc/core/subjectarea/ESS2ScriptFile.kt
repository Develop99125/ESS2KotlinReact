package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESS2ScriptFile{
    var oid:Long=0
    var valid:Boolean=false
    var file:EntityLink<Artifact> = EntityLink<Artifact>()
    var ESS2Architecture:EntityLink<ESS2Architecture> = EntityLink<ESS2Architecture>()
    var scriptType:Int=0
    var serverScript:Boolean=false
    var preCompiled:Boolean=false
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
