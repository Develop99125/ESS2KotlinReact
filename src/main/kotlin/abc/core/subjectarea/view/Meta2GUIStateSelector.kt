package abc.core.subjectarea.view
import RComponent
import RGB
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink
import abc.core.subjectarea.equipment.Meta2StateRegister
import csstype.Position
import csstype.px
import csstype.rgb
import emotion.react.css
import kotlinx.serialization.encodeToString
import react.ChildrenBuilder
import react.Props
import react.State
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import toOneWord

@Serializable
class Meta2GUIStateSelector : RComponent<Props, State>() {
    var oid:Long=0
    var valid:Boolean=false
    var W2:Int=0
    var intValue:Boolean=false
    var byteSize:Boolean=false
    var afterPoint:Int=0
    var type:Int=0
    var x:Int=0
    var y:Int=0
    var dx:Int=0
    var h:Int=0
    var fontSize:Int=0
    var color:Int=0
    var commonColor:Boolean=false
    var backColor:Boolean=false
    var onCenter:Boolean=false
    var bold:Boolean=false
    var labelColor:Int=0
    var labelCommonColor:Boolean=false
    var labelBold:Boolean=false
    var labelOnCenter:Boolean=false
    var labelOnRight:Boolean=false
    var labelBackColor:Boolean=false
    var noEditThere:Boolean=false
    var stringSize:Int=0
    var shortName:String=""
    var title:String=""
    var comment:String=""
    override var regNum:Meta2RegLink = Meta2RegLink()
    //constructor() {}

    override fun clone() : Meta2GUIStateSelector{
        val json = Json.encodeToString(this)
        return Json.decodeFromString<Meta2GUIStateSelector>(json)
    }

    override fun ChildrenBuilder.render() {
        val rgb = RGB(color).split(",").toTypedArray()
        val newX = x + dx + DefaultSpace
        val newY = y + 10
        select{
            css {
                position = Position.absolute
                top = newY.px
                left = newX.px
            }
            val register = regNum.register as Meta2StateRegister
            for(state in register.states.list!!.states){
                option{
                    +state.title
                }
            }
        }
    }

    override fun putValue(vv: Long) {

    }

    override fun putValue(data: ArrayList<Int>) {
        putValue(toOneWord(data))
    }
}
