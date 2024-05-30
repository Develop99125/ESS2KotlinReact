package abc.core.subjectarea.equipment
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class Meta2GUIElement{
    var oid:Long=0
    var valid:Boolean=false
    var type:Int=0
    var x:Int=0
    var y:Int=0
    var dx:Int=0
    var h:Int=0
    var bitMask:Int=0
    var bitNum:Int=0
    var power:Int=0
    var color:Int=0
    var colorYes:Int=0
    var colorNo:Int=0
    var fontSize:Int=0
    var w2:Int=0
    var size:Int=0
    var step:Int=0
    var formName:String=""
    var formLevel:Int=0
    var clickFormName:String=""
    var count:Int=0
    var groupNum:Int=0
    var noEditThere:Boolean=false
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
