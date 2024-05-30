package abc.core.subjectarea.equipment
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class Meta2Equipment{
    var oid:Long=0
    var valid:Boolean=false
    var versionRegNum:Int=0
    var versionRegValue:Int=0
    var versionRegName:String=""
    var outsideName:String=""
    var fileName:String=""
    var titleEng:String=""
    var protocolId:Int=0
    var readOnly:Boolean=false
    var registers:Meta2Collection=Meta2Collection()
    var xmlType:Int=0
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
