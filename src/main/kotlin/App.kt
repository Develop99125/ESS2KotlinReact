import abc.core.subjectarea.view.Meta2GUIForm
import abc.core.subjectarea.view.Meta2GUIFormButton
import csstype.LineStyle.Companion.solid
import emotion.react.css
import kotlinx.coroutines.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import react.*
import react.dom.*
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.nav
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.tr
import react.dom.html.ReactHTML.select as select


external interface AppProps : Props {
    var idForm : Int
    var navigationButtonsLevel0 : ArrayList<Meta2GUIFormButton>
    var navigationButtonsLevel1 : ArrayList<Meta2GUIFormButton>
    var navigationButtonsLevel2 : ArrayList<Meta2GUIFormButton>
    var isSetting : Boolean
}

external interface AppState : State {
    var idForm : Int
    var navigationButtonsLevel0 : ArrayList<Meta2GUIFormButton>
    var navigationButtonsLevel1 : ArrayList<Meta2GUIFormButton>
    var navigationButtonsLevel2 : ArrayList<Meta2GUIFormButton>
    var isSetting : Boolean
}

var blocked = false
var updated = false

@OptIn(DelicateCoroutinesApi::class)
val App = FC<AppProps> { props ->
    var appState by useState<AppState>(object : AppState {
        override var idForm = props.idForm
        override var navigationButtonsLevel0 = props.navigationButtonsLevel0
        override var navigationButtonsLevel1 = props.navigationButtonsLevel1
        override var navigationButtonsLevel2 = props.navigationButtonsLevel2
        override var isSetting = props.isSetting
    })

    fun renderButtons(buttons: ArrayList<Meta2GUIFormButton>){
        for(button in buttons) {
            child(button.render())
        }
    }

    fun setFunctions(buttons: ArrayList<Meta2GUIFormButton>) {
        for(button in buttons) {
            button.state.onSelectForm = { id : Int ->
                blocked = true
                appState = object : AppState {
                    val array = renderViewGet(id)
                    override var idForm = id
                    override var navigationButtonsLevel0 = array[0]
                            as ArrayList<Meta2GUIFormButton>
                    override var navigationButtonsLevel1 = array[1]
                            as ArrayList<Meta2GUIFormButton>
                    override var navigationButtonsLevel2 = array[2]
                            as ArrayList<Meta2GUIFormButton>
                    override var isSetting = array[4] as Boolean
                }
                updated = true
                blocked = false
            }
        }
    }

    setFunctions(appState.navigationButtonsLevel0)
    setFunctions(appState.navigationButtonsLevel1)
    setFunctions(appState.navigationButtonsLevel2)

    if(!appState.isSetting) {
        div {
            for (item in guiList) {
                child(item.render())
            }
        }

        div {
            renderButtons(appState.navigationButtonsLevel0)
        }

        div {
            renderButtons(appState.navigationButtonsLevel1)
        }

        div {
            renderButtons(appState.navigationButtonsLevel2)
        }

        GlobalScope.launch {
            delay(2000)
            if (!blocked && !updated) {
                generateRegValues()
                appState = object : AppState {
                    override var idForm = appState.idForm
                    override var navigationButtonsLevel0 = appState.navigationButtonsLevel0
                    override var navigationButtonsLevel1 = appState.navigationButtonsLevel1
                    override var navigationButtonsLevel2 = appState.navigationButtonsLevel2
                    override var isSetting = appState.isSetting
                }
            }
            updated = false
        }
    }
    else{
        var selectedEquip = 0
        var selectedGUI = 0
        div{
            p{+"Оборудование:"}
            select{
                for(vv in archFull.equipments){
                    option{
                        +vv.title
                    }
                }
            }
        }

        div{
            p{+"ЧМИ:"}
            select{
                for(i in 0 until archFull.views.size){
                    option{
                        onSelect = {
                            selectedGUI = i
                        }
                        +archFull.views[i].title
                    }
                }
            }
        }
        button{
            onClick = {
                GlobalScope.launch {
                    println(selectedGUI)
                    selectGUI(selectedGUI)
                    appState = object : AppState
                    {
                        val array = renderViewGet(1)
                        override var idForm = 1
                        override var navigationButtonsLevel0 = array[0] as ArrayList<Meta2GUIFormButton>
                        override var navigationButtonsLevel1 = array[1] as ArrayList<Meta2GUIFormButton>
                        override var navigationButtonsLevel2 = array[2] as ArrayList<Meta2GUIFormButton>
                        override var isSetting = array[4] as Boolean
                    }
                }
            }
            +"Update"
        }
    }
}