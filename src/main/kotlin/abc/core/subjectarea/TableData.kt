package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class TableData{
    var paramList:DocumentParamList=DocumentParamList()
    var verticalHeader:Boolean=false
    var fileName:String=""
    var tableType:Int=0
    var title:String=""
    constructor() {}
}
