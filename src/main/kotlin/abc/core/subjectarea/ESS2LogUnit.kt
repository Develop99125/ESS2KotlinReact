package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESS2LogUnit{
    var oid:Long=0
    var valid:Boolean=false
    var ESS2Equipment:EntityLink<ESS2Equipment> = EntityLink<ESS2Equipment>()
    var device:EntityLink<ESS2Device> = EntityLink<ESS2Device>()
    var unit:Int=0
    var snapShotDisable:Boolean=false
    var devName:String=""
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
