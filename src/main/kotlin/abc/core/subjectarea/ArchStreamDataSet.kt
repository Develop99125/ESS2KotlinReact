package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ArchStreamDataSet{
    var oid:Long=0
    var valid:Boolean=false
    var nodeOid:Long=0
    var createTime:OwnDateTime=OwnDateTime()
    var streamDataType:Int=0
    var ready:Boolean=false
    var size:Int=0
    var compressMode:Int=0
    var sendToHost:Boolean=false
    var packedValues:String=""
    constructor() {}
}
