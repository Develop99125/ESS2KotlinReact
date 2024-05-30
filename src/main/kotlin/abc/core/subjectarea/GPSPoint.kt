package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class GPSPoint{
    var oid:Long=0
    var valid:Boolean=false
    var state:Int=0
    var geoy:Double=0.0
    var geox:Double=0.0
    var gpsTime:OwnDateTime=OwnDateTime()
    var parentName:String=""
    var parentOid:Long=0
    constructor() {}
}
