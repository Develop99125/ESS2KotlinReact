package abc.core.subjectarea.view

import abc.core.subjectarea.equipment.Meta2Register
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class Meta2RegLink {
    var equipName:String = ""
    var regNum:Int = -2
    var ownUnit:Boolean = false
    var unitIdx: Int = 0
    @Transient var registerValue: Meta2GUIRegValue = Meta2GUIRegValue()
    @Transient var register: Meta2Register = Meta2Register()
}