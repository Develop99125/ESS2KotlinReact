package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESS2View{
    var oid:Long=0
    var valid:Boolean=false
    var ESS2Architecture:EntityLink<ESS2Architecture> = EntityLink<ESS2Architecture>()
    var metaFile:EntityLink<ESS2MetaFile> = EntityLink<ESS2MetaFile>()
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
