package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class MetaGUIForm{
    var oid:Long=0
    var valid:Boolean=false
    var childs:ArrayList<EntityLink<MetaGUIForm>> = ArrayList<EntityLink<MetaGUIForm>>()
    var controls:ArrayList<MetaGUIElement> = ArrayList<MetaGUIElement>()
    var parent:EntityLink<MetaGUIForm> = EntityLink<MetaGUIForm>()
    var level:Int=0
    var parentName:String=""
    var moduleName:String=""
    var empty:Boolean=false
    var noMenu:Boolean=false
    var formLevel:Int=0
    var elementsCount:Int=0
    var accessLevel:Int=0
    var writeLevel:Int=0
    var backImage:String=""
    var regNum:Int=0
    var regBit:Int=0
    var color:Int=0
    var MetaExternalSystem:EntityLink<MetaExternalSystem> = EntityLink<MetaExternalSystem>()
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
