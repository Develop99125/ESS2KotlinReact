package abc.core.subjectarea.view
import RComponent
import RGB
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink
import csstype.Position
import csstype.px
import csstype.rgb
import emotion.react.css
import kotlinx.serialization.*
import kotlinx.serialization.json.Json.Default.encodeToString
import react.FC
import react.Props
import react.dom.html.ReactHTML.p
import react.*
import react.dom.*
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import toOneWord
import kotlin.properties.Delegates
import kotlin.reflect.KMutableProperty

@Serializable
class Meta2GUI2StateBoxSmall : RComponent<Props, State>() {
    var oid: Long = 0
    var valid: Boolean = false
    var bitNum: Int = 0
    var colorYes: Int = 0
    var colorNo: Int = 0
    var buttonSize: Int = 0
    var remoteEnable: Boolean = false
    var mixedRegister: Boolean = false
    var failMode: Boolean = false
    var twoUnits: Boolean = false
    var disableIndexOut: Int = 0
    var disableIndexIn: Int = 0
    var W2: Int = 0
    var intValue: Boolean = false
    var byteSize: Boolean = false
    var afterPoint: Int = 0
    var type: Int = 0
    var x: Int = 0
    var y: Int = 0
    var dx: Int = 0
    var h: Int = 0
    var fontSize: Int = 0
    var color: Int = 0
    var commonColor: Boolean = false
    var backColor: Boolean = false
    var onCenter: Boolean = false
    var bold: Boolean = false
    var labelColor: Int = 0
    var labelCommonColor: Boolean = false
    var labelBold: Boolean = false
    var labelOnCenter: Boolean = false
    var labelOnRight: Boolean = false
    var labelBackColor: Boolean = false
    var noEditThere: Boolean = false
    var stringSize: Int = 0
    var shortName: String = ""
    var title: String = ""
    var comment: String = ""
    var isColorYes: Boolean = false

    override var regNum:Meta2RegLink = Meta2RegLink()
    //constructor() {}

    override fun clone() : Meta2GUI2StateBoxSmall{
        val json = Json.encodeToString(this)
        return Json.decodeFromString<Meta2GUI2StateBoxSmall>(json)
    }

    override fun ChildrenBuilder.render() {
        val rgb : Array<String>
        if(isColorYes) rgb = RGB(65280).split(",").toTypedArray()
        else rgb = RGB(16711680).split(",").toTypedArray()
        var hh = h
        if (hh == 0) hh = 25
        var dd = W2
        if(dd == 0) dd = 50
        val sz = 35
        val offset = (25 - sz)/2
        var newX = x + dx + dxOffset + dd - 5 + offset
        var newY = y + dyOffset + (hh-15)/2 - 5 + offset
        ReactHTML.p {
            css {
                position = Position.absolute
                top = newY.px
                left = newX.px
                color = rgb(rgb[0].toInt(), rgb[1].toInt(), rgb[2].toInt())
                fontSize = if (this@Meta2GUI2StateBoxSmall.fontSize != 0) this@Meta2GUI2StateBoxSmall.fontSize.px else DefaultTextSize.px
            }
            +"${this@Meta2GUI2StateBoxSmall.title}${regOffset}"
        }
    }

    override fun putValue(vv: Long) {
        //
    }

    override fun putValue(data: ArrayList<Int>) {
        putValue(toOneWord(data))
    }
}