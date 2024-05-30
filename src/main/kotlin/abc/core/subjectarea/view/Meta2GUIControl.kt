package abc.core.subjectarea.view

import kotlinx.serialization.Serializable

@Serializable
class Meta2GUIControl {
    var shortName: String=""
    var title: String=""
    var comment: String=""
    var type: Int=0
    var x: Int=0
    var y: Int=0
    var dx: Int=0
    var h: Int=0
    var fontSize: Int=0
    var color: Int=0
    var commonColor: Boolean=false
    var backColor: Boolean=false
    var onCenter: Boolean=false
    var bold: Boolean=false
    var labelColor: Int=0
    var labelCommonColor: Boolean=false
    var labelBold: Boolean=false
    var labelOnCenter: Boolean=false
    var labelOnRight: Boolean=false
    var labelBackColor: Boolean=false
    var noEditThere: Boolean=false
    var stringSize: Int=0
    var list: Meta2JsonGUIList? = null
}