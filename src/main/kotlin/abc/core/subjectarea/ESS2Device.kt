package abc.core.subjectarea
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink

@Serializable
class ESS2Device{
    var oid:Long=0
    var valid:Boolean=false
    var ESS2Architecture:EntityLink<ESS2Architecture> = EntityLink<ESS2Architecture>()
    var IP:String=""
    var port:Int=0
    var dbEmulator:Boolean=false
    var timeOut:Int=0
    var regsInBlock:Int=0
    var unitsNum:Int=0
    var RTU:Boolean=false
    var baudRate:Int=0
    var trace:Boolean=false
    var uniqueModule:Boolean=false
    var uniqueModuleClass:String=""
    var shortName:String=""
    var title:String=""
    var comment:String=""
    constructor() {}

    private var valuesCash: HashMap<Int, HashMap<Int, Int>> = HashMap()
    private var shapShotCash: HashMap<Int, HashMap<Int, Short>> = HashMap()


    fun getValue(unitIdx: Int, regNum: Int) : Int? {
        val unitMap = this.valuesCash[unitIdx]
        return if(unitMap == null) null else unitMap[regNum]
    }

    fun putValue(unitIdx: Int, regNum: Int, value: Int) {
        var unitMap = this.valuesCash[unitIdx]
        if (unitMap == null) {
            unitMap = HashMap()
            this.valuesCash[unitIdx] = unitMap
        }

        unitMap[regNum] = value
        //println("Value is ${unitMap[regNum]}")
    }

    fun createList(withValues: Boolean): ArrayList<UnitRegisterList> {
        val out = ArrayList<UnitRegisterList>()
        val bb = this.valuesCash.keys
        for (cc in bb) {
            //println("CreateList: $cc")
            val map = this.valuesCash[cc] as HashMap<Int, Int>
            val list2 = UnitRegisterList()
            list2.unitIdx = cc
            val dd = map.keys
            for (ee in dd) {
                list2.add(ee)
                if (withValues) {
                    list2.add(map[ee] as Int)
                }
            }
            if (list2.size() != 0) {
                out.add(list2)
            }
        }
        //println(out.size)
        return out
    }

    fun clearCash() {
        this.valuesCash.clear()
    }

    fun clearCash(unitIdx: Int) {
        val unitMap = this.valuesCash[unitIdx]
        if (unitMap != null) {
            unitMap.clear()
        }
    }
}
