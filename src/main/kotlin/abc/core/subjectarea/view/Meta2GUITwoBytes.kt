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
import csstype.rgb
import emotion.react.css
import kotlinx.serialization.encodeToString
import react.ChildrenBuilder
import react.Props
import react.State
import react.dom.html.ReactHTML
import toOneWord

@Serializable
class Meta2GUITwoBytes : RComponent<Props, State>() {
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
    override var regNum: Meta2RegLink = Meta2RegLink()
    //constructor() {}

    override fun clone() : Meta2GUITwoBytes{
        val json = Json.encodeToString(this)
        return Json.decodeFromString<Meta2GUITwoBytes>(json)
    }

    override fun ChildrenBuilder.render() {
        var rgb = RGB(color).split(",").toTypedArray()
        val newX = x
        ReactHTML.p {
            css {
                position = Position.absolute
                top = y.px
                left = newX.px
                color = rgb(rgb[0].toInt(), rgb[1].toInt(), rgb[2].toInt())
                fontSize = if (this@Meta2GUITwoBytes.fontSize != 0) this@Meta2GUITwoBytes.fontSize.px else DefaultTextSize.px
            }
            +"${this@Meta2GUITwoBytes.title}, ${regNum.registerValue.stringValue}"
        }
    }

    override fun putValue(vv: Long) {
        regNum.registerValue.stringValue = "[${ (vv shr 8) and 0x0FF }][${vv and 0x0FF}]"
    }

    override fun putValue(data: ArrayList<Int>) {
        putValue(toOneWord(data))
    }
}
