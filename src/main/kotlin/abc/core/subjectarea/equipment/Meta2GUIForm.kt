package abc.core.subjectarea.equipment
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink
import abc.core.subjectarea.view.Meta2GUICollection

@Serializable
class Meta2GUIForm{
    var oid:Long=0
    var valid:Boolean=false
    var controls: Meta2GUICollection =Meta2GUICollection()
    var level:Int=0
    var parentName:String=""
    var moduleName:String=""
    var moduleX0:Int=0
    var moduleY0:Int=0
    var moduleDX:Int=0
    var moduleDY:Int=0
    var empty:Boolean=false
    var noMenu:Boolean=false
    var formLevel:Int=0
    var elementsCount:Int=0
    var accessLevel:Int=0
    var writeLevel:Int=0
    var backImage:String=""
    var picture:EntityLink<Artifact> = EntityLink<Artifact>()
    var iconImage:String=""
    var icon:EntityLink<Artifact> = EntityLink<Artifact>()
    var imageX0:Int=0
    var imageY0:Int=0
    var imageW:Int=0
    var imageH:Int=0
    var regNum:Int=0
    var regBit:Int=0
    var color:Int=0
    var baseForm:Boolean=false
    var linkForm:Boolean=false
    var baseFormIndex:Int=0
    var snapShot:Boolean=false
    var debugForm:Boolean=false
    var menuButtonW:Int=0
    var menuButtonH:Int=0
    var menuButtonTextColor:Int=0
    var menuButtonOffColor:Int=0
    var menuButtonOnColor:Int=0
    var menuButtonFontSize:Int=0
    var menuFontBold:Boolean=false
    var formScrollScale:Double=0.0
    var scrollHorizontal:Boolean=false
    var noEditThere:Boolean=false
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
