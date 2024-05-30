package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class Person{
    var oid:Long=0
    var valid:Boolean=false
    var lastName:String=""
    var firstName:String=""
    var middleName:String=""
    var phone:Phone=Phone()
    var mail:Mail=Mail()
    var post:String=""
    constructor() {}
}
