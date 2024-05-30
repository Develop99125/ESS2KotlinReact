package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESS2Architecture{
    var oid:Long=0
    var valid:Boolean=false
    var views:ArrayList<ESS2View> = ArrayList()
    var equipments:ArrayList<ESS2Equipment> = ArrayList()
    var devices:ArrayList<ESS2Device> = ArrayList()
    var scripts:ArrayList<ESS2ScriptFile> = ArrayList()
    var emulators:ArrayList<ESS2EquipEmulator> = ArrayList()
    var profilers:ArrayList<ESS2ProfilerModule> = ArrayList()
    var envValues:ArrayList<ESS2EnvValue> = ArrayList()
    var gates:ArrayList<ESS2ModBusGate> = ArrayList()
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}

    fun getDeviceById(id: Long) : ESS2Device? {
        for(device in devices){
            if (device.oid == id) return device
        }
        return null
    }
}
