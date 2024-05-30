package abc.core.subjectarea.view
import RComponent
import RGB
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink
import csstype.Position
import csstype.px
import emotion.react.css
import kotlinx.serialization.encodeToString
import react.ChildrenBuilder
import react.Props
import react.State
import react.dom.html.ReactHTML
import toOneWord

@Serializable
class Meta2GUIBitCheckBox : RComponent<Props, State>() {
    var oid:Long=0
    var valid:Boolean=false
    var bitNum:Int=0
    var colorYes:Int=0
    var colorNo:Int=0
    var buttonSize:Int=0
    var remoteEnable:Boolean=false
    var mixedRegister:Boolean=false
    var failMode:Boolean=false
    var twoUnits:Boolean=false
    var disableIndexOut:Int=0
    var disableIndexIn:Int=0
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

    override fun clone() : Meta2GUIBitCheckBox{
        val json = Json.encodeToString(this)
        return Json.decodeFromString<Meta2GUIBitCheckBox>(json)
    }

    override fun ChildrenBuilder.render() {
        val rgb = RGB(color).split(",").toTypedArray()
        ReactHTML.p {
            css {
                position = Position.absolute
                top = y.px
                left = x.px
                //color = rgb(rgb[0].toInt(), rgb[1].toInt(), rgb[2].toInt())
                fontSize = if (this@Meta2GUIBitCheckBox.fontSize != 0) this@Meta2GUIBitCheckBox.fontSize.px else DefaultTextSize.px
            }
            +"${this@Meta2GUIBitCheckBox.title} ${regNum.registerValue}"
        }
    }

    override fun putValue(vv: Long) {

    }

    override fun putValue(data: ArrayList<Int>) {
        putValue(toOneWord(data))
    }
}