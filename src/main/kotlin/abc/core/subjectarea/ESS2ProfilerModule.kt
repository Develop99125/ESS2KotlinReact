package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESS2ProfilerModule{
    var oid:Long=0
    var valid:Boolean=false
    var ESS2Architecture:EntityLink<ESS2Architecture> = EntityLink<ESS2Architecture>()
    var enable:Boolean=false
    var trace:Boolean=false
    var className:String=""
    var filePath:String=""
    var profileData:EntityLink<Artifact> = EntityLink<Artifact>()
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
