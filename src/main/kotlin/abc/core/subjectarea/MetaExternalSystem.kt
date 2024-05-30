package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class MetaExternalSystem{
    var oid:Long=0
    var valid:Boolean=false
    var versionRegNum:Int=0
    var versionRegValue:Int=0
    var subSystems:ArrayList<MetaSubSystem> = ArrayList<MetaSubSystem>()
    var states:ArrayList<MetaState> = ArrayList<MetaState>()
    var sources:ArrayList<MetaDataRegister> = ArrayList<MetaDataRegister>()
    var settings:ArrayList<MetaSettingRegister> = ArrayList<MetaSettingRegister>()
    var bitSets:ArrayList<MetaBitRegister> = ArrayList<MetaBitRegister>()
    var command:ArrayList<MetaCommandRegister> = ArrayList<MetaCommandRegister>()
    var forms:ArrayList<MetaGUIForm> = ArrayList<MetaGUIForm>()
    var configs:ArrayList<PLMConfig> = ArrayList<PLMConfig>()
    var fileModule:ArrayList<MetaFileModule> = ArrayList<MetaFileModule>()
    var importErrorsCount:Int=0
    var importErrorsString:String=""
    var essHost:ArrayList<MetaGUIElement> = ArrayList<MetaGUIElement>()
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
