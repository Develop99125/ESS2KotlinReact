package abc.core.subjectarea.view

import RComponent
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import react.ChildrenBuilder
import react.Props
import react.State

@Serializable
class Meta2GUIView : RComponent<Props, State> {
    var shortName: String=""
    var title: String=""
    var comment: String=""
    var xmlType: Int=0
    var backColor: Int=0
    var textColor: Int=0
    var commonBackColor: Int=0
    var height: Int=0
    var width: Int=0
    var menuModes: Int=0
    var menuButtonW: Int=0
    var menuButtonH: Int=0
    var menuButtonTextColor: Int=0
    var menuButtonOffColor: Int=0
    var menuButtonOnColor: Int=0
    var menuButtonFontSize: Int=0
    var menuFontBold: Boolean=false
    var noEditThere: Boolean=false
    var forms: Meta2GUIFormFake = Meta2GUIFormFake()
    constructor()

    override fun clone() : Meta2GUIView{
        val json = Json.encodeToString(this)
        return Json.decodeFromString<Meta2GUIView>(json)
    }

    override fun ChildrenBuilder.render() {
        TODO("Not yet implemented")
    }

    override fun putValue(vv: Long) {
        TODO("Not yet implemented")
    }

    override fun putValue(data: ArrayList<Int>) {
        //putValue(toOneWord(data))
    }
}