package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class WorkSettingsBase{
    var oid:Long=0
    var valid:Boolean=false
    var MKVersion:String=""
    var dataServerFileDir:String=""
    var dataServerFileDirDefault:Boolean=false
    var convertAtrifact:Boolean=false
    var mailHost:String=""
    var mailBox:String=""
    var mailPass:String=""
    var mailSecur:String=""
    var mailPort:Int=0
    var mailToSend:String=""
    var mailNotifycation:Boolean=false
    var nodeName:String=""
    var traceLevel:Int=0
    constructor() {}
}
