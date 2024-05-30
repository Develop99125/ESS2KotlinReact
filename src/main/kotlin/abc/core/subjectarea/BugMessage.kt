package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class BugMessage{
    var oid:Long=0
    var valid:Boolean=false
    var user:EntityLink<User> = EntityLink<User>()
    var date:OwnDateTime=OwnDateTime()
    var message:String=""
    constructor() {}
}
