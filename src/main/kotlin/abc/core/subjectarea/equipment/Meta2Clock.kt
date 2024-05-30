package abc.core.subjectarea.equipment
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class Meta2Clock : Meta2Register{
    var oid:Long=0
    var valid:Boolean=false
    var DOType:String=""
    override var unit:String=""
    override var power:Int=0
    override var regNum:Int=0
    override var format:Int=0
    override var type:Int=0
    override var snapShot:Boolean=false
    override var envVar:String=""
    override var valueWord:Int=0
    override var valueWordSaved:Int=0
    override var IEC60870RegNum:Int=0
    var out61850Model:Boolean=false
    var in60870Model:Boolean=false
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
