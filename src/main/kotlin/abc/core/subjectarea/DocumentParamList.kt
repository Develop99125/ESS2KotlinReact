package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class DocumentParamList{
    var landscape:Boolean=false
    var full:Boolean=false
    var nLines:Int=0
    constructor() {}
}
