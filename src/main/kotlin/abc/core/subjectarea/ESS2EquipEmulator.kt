package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESS2EquipEmulator{
    var oid:Long=0
    var valid:Boolean=false
    var ESS2Architecture:EntityLink<ESS2Architecture> = EntityLink<ESS2Architecture>()
    var enable:Boolean=false
    var trace:Boolean=false
    var port:Int=0
    var className:String=""
    var lineName:String=""
    var RTU:Boolean=false
    var baudRate:Int=0
    var metaFile:EntityLink<ESS2MetaFile> = EntityLink<ESS2MetaFile>()
    var authCode:String=""
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
