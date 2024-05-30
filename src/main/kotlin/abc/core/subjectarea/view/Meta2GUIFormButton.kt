package abc.core.subjectarea.view
import RComponent
import RGB
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink
import csstype.*
import emotion.react.css
import kotlinx.browser.window
import kotlinx.serialization.encodeToString
import react.ChildrenBuilder
import react.Props
import react.State
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.style
import toOneWord

external interface FormButtonProps : Props {
    var selectedForm : Int
    var onSelectForm: (Int) -> Unit
}

external interface FormButtonState : State {
    var selectedForm : Int
    var onSelectForm: (Int) -> Unit
}

class Meta2GUIFormButton(props: FormButtonProps) : RComponent<FormButtonProps, FormButtonState>(props) {
    var oid:Long=0
    var valid:Boolean=false
    var formName:String=""
    var ownUnit:Boolean=false
    override var unitIdx:Int=0
    var unitLevel:Int=0
    var onlyIndex:Boolean=false
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
    var idForm: Int = 0
    var level: Int = 0
    //constructor() {}

    override fun clone() : RComponent<FormButtonProps, FormButtonState> {
        val json = Json.encodeToString(this)
        return this
    }

    override fun FormButtonState.init(prps: FormButtonProps){
        selectedForm = idForm
        onSelectForm = {}
    }


    override fun ChildrenBuilder.render() {
        //val rgb = RGB(color).split(",").toTypedArray()
        button {
            css {
                position = Position.relative
                top = y.px
                left = x.px
                backgroundColor = if (1 == 1) NamedColor.lightgreen else NamedColor.red
            }
            onClick = {
                state.onSelectForm(idForm)
            }
            +"${this@Meta2GUIFormButton.title}"
        }
    }

    override fun putValue(vv: Long) {
        TODO("Not yet implemented")
    }

    override fun putValue(data: ArrayList<Int>) {
        putValue(toOneWord(data))
    }
}
