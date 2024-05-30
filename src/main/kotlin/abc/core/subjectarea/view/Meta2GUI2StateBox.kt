package abc.core.subjectarea.view
import RComponent
import RGB
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink
import abc.core.subjectarea.equipment.Meta2Bit
import abc.core.subjectarea.equipment.Meta2BitRegister
import csstype.Position
import csstype.px
import csstype.rgb
import emotion.react.css
import kotlinx.serialization.encodeToString
import react.ChildrenBuilder
import react.Props
import react.State
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import toOneWord

@Serializable
class Meta2GUI2StateBox : RComponent<Props, State>() {
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
    var isColorYes = false
    var lastBitValue = -1
    var lastValue = 0
    override var regNum: Meta2RegLink = Meta2RegLink()
    var buttonText: String = ""
    var rgb : Array<String> = arrayOf("0", "0", "0")
    //constructor() {}

    override fun clone() : Meta2GUI2StateBox{
        val json = Json.encodeToString(this)
        return Json.decodeFromString<Meta2GUI2StateBox>(json)
    }

    override fun ChildrenBuilder.render() {
//        if(isColorYes) rgb = RGB(65280).split(",").toTypedArray()
//        else if() rgb = RGB(16711680).split(",").toTypedArray()
        var hh = h
        if (hh == 0) hh = 25
        var dd = W2
        if (dd == 0) dd = 50
        val sz = 35
        val offset = (25 - sz) / 2
        var newX = x
        var newY = y
        if (dx == 0) {
            newX = x + dx + dxOffset + dd - 5 + offset
            newY = y + dyOffset + (hh - 15) / 2 - 5 + offset
        }
        if (this@Meta2GUI2StateBox.title != "") {
            ReactHTML.p {
                css {
                    position = Position.absolute
                    top = newY.px
                    left = newX.px
                    //color = rgb(rgb[0].toInt(), rgb[1].toInt(), rgb[2].toInt())
                    fontSize =
                        if (this@Meta2GUI2StateBox.fontSize != 0) this@Meta2GUI2StateBox.fontSize.px else DefaultTextSize.px
                }
                +"${this@Meta2GUI2StateBox.title}"
            }
            var value = this@Meta2GUI2StateBox.title.length * 7
            if (value >= 300) value = 305
            var tNewX = newX + value
            ReactHTML.p {
                css {
                    position = Position.absolute
                    top = newY.px
                    left = tNewX.px
                    //color = rgb(rgb[0].toInt(), rgb[1].toInt(), rgb[2].toInt())
                    fontSize =
                        if (this@Meta2GUI2StateBox.fontSize != 0) this@Meta2GUI2StateBox.fontSize.px else DefaultTextSize.px
                    border = 2.px
                }
                +"O"
            }
        }
        else {
            ReactHTML.p {
                css {
                    position = Position.absolute
                    top = newY.px
                    left = newX.px
                    color = rgb(rgb[0].toInt(), rgb[1].toInt(), rgb[2].toInt())
                    fontSize =
                        if (this@Meta2GUI2StateBox.fontSize != 0) this@Meta2GUI2StateBox.fontSize.px else DefaultTextSize.px
                }
                +"O"
            }
        }
//        val register = regNum.register as Meta2BitRegister
//        var bit = register.getBitByCode(bitNum)
        if (buttonSize == 0) return
        if(this@Meta2GUI2StateBox.title != "") {
            var value = this@Meta2GUI2StateBox.title.length * 7
            if (value >= 300) value = 305
            newX += value
        }
        newX += 20
        newY += 13
        button {
            onClick = {

            }
            css {
                position = Position.absolute
                top = newY.px
                left = newX.px
                //color = rgb(rgb[0].toInt(), rgb[1].toInt(), rgb[2].toInt())
                fontSize =
                    if (this@Meta2GUI2StateBox.fontSize != 0) this@Meta2GUI2StateBox.fontSize.px else DefaultTextSize.px
            }
            +buttonText
        }
    }

    override fun putValue(vv: Long) {
        lastValue = vv.toInt()
        lastBitValue = (lastValue shr bitNum) and 0b1
//        if (lastBitValue != 0) isColorYes = true
//        else isColorYes = false"15790320"
        if(lastBitValue > 0) rgb = RGB(65280).split(",").toTypedArray()
        else if(lastBitValue == 0) rgb = RGB(16711680).split(",").toTypedArray()
        else rgb = RGB(15790320).split(",").toTypedArray()
        if(lastBitValue != 0) buttonText = "ОТКЛ"
        else buttonText = "ВКЛ"
    }

    override fun putValue(data: ArrayList<Int>) {
        putValue(toOneWord(data))
    }
}
