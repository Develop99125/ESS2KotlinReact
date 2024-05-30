package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class User{
    var oid:Long=0
    var valid:Boolean=false
    var typeId:Int=0
    var accountData:EntityLink<Account> = EntityLink<Account>()
    var photo:EntityLink<Artifact> = EntityLink<Artifact>()
    var secondTableId:Long=0
    var sessionToken:String=""
    var simCardICC:String=""
    var lastName:String=""
    var firstName:String=""
    var middleName:String=""
    var phone:Phone=Phone()
    var mail:Mail=Mail()
    var post:String=""
    constructor() {}
}
