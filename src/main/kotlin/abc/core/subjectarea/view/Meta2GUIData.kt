package abc.core.subjectarea.view
import RComponent
import RGB
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink
import abc.core.subjectarea.equipment.Meta2Collection
import abc.core.subjectarea.equipment.Meta2Equipment
import csstype.*
import emotion.react.css
import kotlinx.serialization.Contextual
import kotlinx.serialization.encodeToString
import react.ChildrenBuilder
import react.Props
import react.State
import react.dom.html.ReactHTML
import toOneWord

@Serializable
class Meta2GUIData : RComponent<Props, State>() {
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

    override fun clone() : Meta2GUIData{
        val json = Json.encodeToString(this)
        return Json.decodeFromString<Meta2GUIData>(json)
    }

    override fun ChildrenBuilder.render() {
        val rgb = RGB(color).split(",").toTypedArray()
        val newX = x
        val newY = y
        ReactHTML.p {
            css {
                position = Position.absolute
                top = newY.px
                left = newX.px
                //color = rgb(rgb[0].toInt(), rgb[1].toInt(), rgb[2].toInt())
                fontSize = if (this@Meta2GUIData.fontSize != 0) this@Meta2GUIData.fontSize.px
                else DefaultTextSize.px
            }
            var tempTitle = ""
            if (W2 != 120) tempTitle = this@Meta2GUIData.title
            +"$tempTitle [${regNum.register.unit}], ${regNum.registerValue.stringValue}"
        }
    }

    override fun putValue(vv: Long) {
        regNum.registerValue.stringValue = regNum.register.regValueToString(regNum.unitIdx, vv.toInt(), Meta2GUIRegW2(W2, intValue, byteSize, afterPoint))
    }

    override fun putValue(data: ArrayList<Int>) {
        putValue(toOneWord(data))
    }
}












