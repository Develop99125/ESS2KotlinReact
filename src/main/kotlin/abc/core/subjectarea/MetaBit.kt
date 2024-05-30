package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class MetaBit{
    var oid:Long=0
    var valid:Boolean=false
    var MetaBitRegister:EntityLink<MetaBitRegister> = EntityLink<MetaBitRegister>()
    var bitNum:Int=0
    var control:Boolean=false
    var bitRegNum:Int=0
    var dataRegNum:Int=0
    var settingRegNum:Int=0
    var bitType:Int=0
    var invert:Int=0
    var beginTime:OwnDateTime=OwnDateTime()
    var endTime:OwnDateTime=OwnDateTime()
    var prior:Int=0
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
