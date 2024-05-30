package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESS2Config{
    var oid:Long=0
    var valid:Boolean=false
    var author:EntityLink<User> = EntityLink<User>()
    var createDate:OwnDateTime=OwnDateTime()
    var changeDate:OwnDateTime=OwnDateTime()
    var registerValues:ArrayList<ESS2ConfigValue> = ArrayList<ESS2ConfigValue>()
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}
}
