package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESSNode{
    var oid:Long=0
    var valid:Boolean=false
    var hostName:String=""
    var serverIP:String=""
    var serverPort:Int=0
    var innerIP:String=""
    var innerPort:Int=0
    var working:Boolean=false
    var innerWorking:Boolean=false
    var connect:Boolean=false
    var innerTestTime:OwnDateTime=OwnDateTime()
    var stateTestTime:OwnDateTime=OwnDateTime()
    var streamDataTime:OwnDateTime=OwnDateTime()
    var streamRegisters:ArrayList<MetaDataRegister> = ArrayList<MetaDataRegister>()
    var streamRegistersValid:Boolean=false
    var lastStreamDataId:Long=0
    var lastStreamPeriodId:Long=0
    var essConfigName:String=""
    constructor() {}
}
