package abc.core.subjectarea.equipment
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class Meta2BitRegister : Meta2Register{
    var oid:Long=0
    var valid:Boolean=false
    var bitRegType:Int=0
    var homogen:Boolean=false
    var inline61860:Boolean=false
    var control:Boolean=false
    override var unit:String=""
    override var power:Int=0
    override var regNum:Int=0
    override var format:Int=0
    override var type:Int=0
    override var snapShot:Boolean=false
    override var envVar:String=""
    override var valueWord:Int=0
    override var valueWordSaved:Int=0
    override var IEC60870RegNum:Int=0
    var DOType:String=""
    var out61850Model:Boolean=false
    var in60870Model:Boolean=false
    var shortName:String=""
    var title:String=""
    var comment:String=""
    var bits: Meta2BitListWrapper = Meta2BitListWrapper()
    constructor() {}

    fun getBitByCode(code: Int): Meta2Bit? {
        for(bit in bits.list!!.bits){
            if(bit.bitNum == code) return bit
        }
        return null
    }
}
