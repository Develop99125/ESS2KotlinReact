package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESS2ModBusGate{
    var oid:Long=0
    var valid:Boolean=false
    var ESS2Architecture:EntityLink<ESS2Architecture> = EntityLink<ESS2Architecture>()
    var enable:Boolean=false
    var trace:Boolean=false
    var commonRegField:Boolean=false
    var port:Int=4567
    var device:EntityLink<ESS2Device> = EntityLink<ESS2Device>()
    var scriptName:String=""
    var shortName:String=""
    var name:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
