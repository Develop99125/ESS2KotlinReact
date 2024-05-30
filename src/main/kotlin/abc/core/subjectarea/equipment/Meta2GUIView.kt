package abc.core.subjectarea.equipment
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class Meta2GUIView{
    var oid:Long=0
    var valid:Boolean=false
    var backColor:Int=0
    var textColor:Int=0
    var commonBackColor:Int=0
    var height:Int=0
    var width:Int=0
    var menuModes:Int=0
    var menuButtonW:Int=0
    var menuButtonH:Int=0
    var menuButtonTextColor:Int=0
    var menuButtonOffColor:Int=0
    var menuButtonOnColor:Int=0
    var menuButtonFontSize:Int=0
    var menuFontBold:Boolean=false
    var noEditThere:Boolean=false
    var xmlType:Int=0
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
