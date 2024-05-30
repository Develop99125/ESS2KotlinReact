package abc.core.subjectarea.view

import abc.core.subjectarea.equipment.Meta2Array
import kotlinx.serialization.Serializable
import react.ReactNode

@Serializable
class Meta2GUIForm {
    var shortName: String=""
    var title: String=""
    var comment: String=""
    var level: Int=0
    var parentName: String=""
    var moduleName: String=""
    var moduleX0: Int=0
    var moduleY0: Int=0
    var moduleDX: Int=0
    var moduleDY: Int=0
    var empty: Boolean=false
    var noMenu: Boolean=false
    var formLevel: Int=0
    var elementsCount: Int=0
    var accessLevel: Int=0
    var writeLevel: Int=0
    var backImage: String=""
    var imageX0: Int=0
    var imageY0: Int=0
    var imageW: Int=0
    var imageH: Int=0
    var regNum: Int=0
    var regBit: Int=0
    var color: Int=0
    var baseForm: Boolean=false
    var linkForm: Boolean=false
    var baseFormIndex: Int=0
    var snapShot: Boolean=false
    var debugForm: Boolean=false
    var menuButtonW: Int=0
    var menuButtonH: Int=0
    var menuButtonTextColor: Int=0
    var menuButtonOffColor: Int=0
    var menuButtonOnColor: Int=0
    var menuButtonFontSize: Int=0
    var menuFontBold: Boolean=false
    var formScrollScale: Float=0.0F
    var scrollHorizontal: Boolean=false
    var noEditThere: Boolean=false
    var controls: Meta2GUIControl = Meta2GUIControl()

    fun getElementsForRender() : ArrayList<ReactNode?>{
        val arrays = controls.list!!.getArrayOfArrays()
        val result : ArrayList<ReactNode?> = ArrayList()

        for(singleArray in arrays){
            for(element in singleArray){
                result.add(element.render())
            }
        }

        for(element in controls.list!!.guiArrays){
            getElementsForRenderInArray(element, result)
        }

        return result
    }

    private fun getElementsForRenderInArray(array: Meta2GUIArray,
                                            result: ArrayList<ReactNode?>) {
        val arrays = array.elem?.list!!.getArrayOfArrays()

        for(singleArray in arrays){
            for(element in singleArray){
                result.add(element.render())
            }
        }

        for(element in array.elem?.list!!.guiArrays){
            getElementsForRenderInArray(element, result)
        }
    }
}