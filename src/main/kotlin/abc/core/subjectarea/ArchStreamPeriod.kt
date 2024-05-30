package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ArchStreamPeriod{
    var oid:Long=0
    var valid:Boolean=false
    var startServerTime:OwnDateTime=OwnDateTime()
    var shutDownServerTime:OwnDateTime=OwnDateTime()
    var firstStreamTime:OwnDateTime=OwnDateTime()
    var lastStreamTime:OwnDateTime=OwnDateTime()
    var shutDown:Boolean=false
    var node:EntityLink<ESSNode> = EntityLink<ESSNode>()
    var originalPeriodId:Long=0
    var setCount:Int=0
    var sourceByteSize:Int=0
    var packedByteSize:Int=0
    var packedValuesSize:Int=0
    constructor() {}
}
