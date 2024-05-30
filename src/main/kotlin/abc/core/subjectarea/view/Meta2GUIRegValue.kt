package abc.core.subjectarea.view

import kotlinx.serialization.Serializable

class Meta2GUIRegValue {
    var floatValue: Float? = null
    var stringValue: String = ""


    override fun toString(): String {
        if (floatValue == null) return stringValue
        else return floatValue.toString()
    }
}