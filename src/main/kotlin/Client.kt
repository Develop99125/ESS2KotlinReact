
import abc.core.subjectarea.*
import abc.core.subjectarea.equipment.Meta2Equipment
import abc.core.subjectarea.view.*
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.fetch.Headers
import org.w3c.fetch.RequestInit
import react.*
import react.dom.client.createRoot
import react.dom.render
import kotlin.js.json


suspend fun keepAlive2(token:String): String {
    val response = window
        .fetch("http://10.200.200.70:4567/api/keepalive", RequestInit("get", Headers().append("SessionToken",token)))
        .await()
        .text()
        .await()
    return response
}
var token:String=""
val constMap: HashMap<String,ConstValue> = HashMap()
val api : RestAPIBase= RestAPIBase();
val api2 : RestAPIESS2= RestAPIESS2();

//---------------------------------------------------------------------------------------------------------------
@OptIn(DelicateCoroutinesApi::class)
fun finalOut(buttonsLevel0: ArrayList<Meta2GUIFormButton>,
             buttonsLevel1: ArrayList<Meta2GUIFormButton>,
             buttonsLevel2: ArrayList<Meta2GUIFormButton>,
             id: Int, settingBool: Boolean) {
    val container = document.createElement("div")
    document.body!!.appendChild(container)
    val app = App.create {
        idForm = id
        navigationButtonsLevel0 = buttonsLevel0
        navigationButtonsLevel1 = buttonsLevel1
        navigationButtonsLevel2 = buttonsLevel2
        isSetting = settingBool
    }
    createRoot(container).render(app)
}
//-------------------------------------------------------------------------------------------------------------------
suspend fun loadXMLArtifact(art : Artifact, className: String) : String {
    val body = api.downLoadAsString(token,art.oid)
    var result = parseXml(body.data!!,
        if(className == "Equipment") classesEquipment
        else classesGUI)
    if (className == "Equipment"){
        //result = result.replace("bits", "bit")
        classesEquipment.forEach {
            result = result.replace("$it\":[", toCamelCase(it, true) + "\":[")
        }
    }
    else {
        classesGUI.forEach {
            result = result.replace("$it\":[", toCamelCaseGUI(it, true) + "\":[")
        }
    }
    return result
}

fun xmlToJson(xml: String, className: String) : String{
    var result = parseXml(xml,
        if(className == "Equipment") classesEquipment
        else classesGUI)
    if (className == "Equipment"){
        result = result.replace("bits", "bit")
        classesEquipment.forEach {
            result = result.replace("$it\":[",
                toCamelCase(it, true) + "\":[")
        }
    }
    else {
        classesGUI.forEach {
            result = result.replace("$it\":[",
                toCamelCaseGUI(it, true) + "\":[")
        }
    }
    return result
}

/*
val fileSize: Long = body.data!!.contentLength()
val `in`: InputStream = body.byteStream()
val reader = InputStreamReader(`in`, "UTF8")
val buffer = StringBuffer()
var cc: Int
while (reader.read().also { cc = it } != -1) {
    buffer.append(cc.toChar())
}
reader.close()
base.guiCall(object : Runnable() {
    override fun run() {
        back.onSuccess(buffer.toString())
    }
})
*/
// поправить - двойной шаблон
// RestAPIESS2
// suspend fun getStreamData2(SessionToken:String,mode:Int,logNum:Int,timeInMS1:Long,timeInMS2:Long) : R<Pair<ErrorList,ArrayList<StreamDataValue>>> {
// RestAPIBase
// suspend fun getConsoleLogPolling(SessionToken:String,lastnum:Long) : R<Pair<Long,StringList>> {
// api2.ip="http://10.200.200.72"
// api2.port=4567
//

fun toCamelCase(str: String, withS: Boolean): String {
    var result = ""
    val charArray = str.toCharArray()
    charArray[0] = charArray[0].lowercaseChar()
    result = charArray.joinToString("")
    if (withS) result += "s"
    return result
}

fun toCamelCaseGUI(str: String, withS: Boolean): String {
    var result = ""
    val charArray = str.toCharArray()
    for (i in 0..2) { charArray[i] = charArray[i].lowercaseChar() }
    result = charArray.joinToString("")
    if (withS) result += "s"
    return result
}

var equipment: Meta2Equipment = Meta2Equipment()
var view: Meta2GUIView = Meta2GUIView()
var archFull : ESS2Architecture = ESS2Architecture()
var regTool : RenderingWithRegisters? = null
var guiList : ArrayList<RComponent<Props, State>> = ArrayList()

suspend fun main(args: Array<String>) {
    var userPair : R<User> = R<User>()
    do {
        if (userPair.mes != ""){
            window.alert(userPair.mes)
        }
        val loginParams = loginJS().split(",").toTypedArray()
        api.ip = loginParams[0]
        api.port = loginParams[1].toInt()
        api2.ip = loginParams[0]
        api2.port = loginParams[1].toInt()
        userPair = api.login(loginParams[2], loginParams[3])

    } while(!userPair.valid())
//    api.ip = "217.71.138.9"
//    api.port = 4569
//    api2.ip = "217.71.138.9"
//    api2.port = 4569
//    //val ss = api.keepalive("1111111")
//    //------------------------- Авторизация под сисадмином ------------------------
//    val userPair = api.login("9132222222", "1234")
//    if (!userPair.valid()) {
//        //finalOut("Ошибка: "+userPair.mes)
//        return
//    }
    token = userPair.data!!.sessionToken
    val format = Json {
        ignoreUnknownKeys = true
        isLenient = true
        //useArrayPolymorphism = true
    }
//    //---------------------------------------------------------------
    //var list = api.getEntityList(token,"User",0,0)
    //for (vv in list.data!!){
    //     var user2 = format.decodeFromString<User>(vv.jsonObject)
    //     out += user2.lastName+"_"+user2.firstName+"["+user2.oid+"]..."
    //     }
    val cList = api.getConstAll(token)
    if (!cList.valid()) {
        //finalOut("Ошибка: "+cList.mes)
        return
    }
    //---------------------- Таблица констант от сервера ----------------------
    constMap.clear()
    for (cc in cList.data!!) {
        constMap.put(cc.groupName + "." + cc.name, cc)
    }
    var twoLong = api2.getArchitectureState(token)
    if (!twoLong.valid()) {
        println("ESS2 не работает")
        return
    }
    val state = twoLong.data!!.get(0)
    if (twoLong.data!!.get(0).toInt() != constMap.get("ArchState.ASConnected")!!.value) {
        return
    }
    var dd = api.getEntity(token, "ESS2Architecture", twoLong.data!!.get(1), 3)
    if (!dd.valid()) {
        return
    }
    //println(dd.data!!.jsonObject)
    archFull = format.decodeFromString<ESS2Architecture>(dd.data!!.jsonObject)
    for (vv in archFull.equipments) {
        val equipmentJSON = loadXMLArtifact(vv.metaFile.ref!!.file.ref!!, "Equipment")
        //val body = api.downLoadAsString(token, vv.metaFile.ref!!.file.ref!!.oid)
        //val equipmentJSON = xmlToJson(body.data!!, "Equipment")
        //println(equipmentJSON)
        equipment = format.decodeFromString<Meta2Equipment>(equipmentJSON)
        vv.data = equipment
        for(array in vv.data.registers.list!!.arrays) vv.generateHighLinks(array)
        //println(vv.data.registers.list!!.arrays[0].elem.list!!.arrays[0].elem.list!!.dataRegisters[1].getArrayLevel())
        break
    }
    for (vv in archFull.devices) {
//        val body = "{\"unitIdx\" : 1,\"data\" : [1376, 1488, 1600, 1712, 1824, 1936, 2048, 2160]}"
//        val values = api2.readESS2RegistersValues(token, vv.shortName, body)
//        for(value in values.data!!){
//            println(value)
//        }
//        val list = UnitRegisterList()
//        list.unitIdx = 1
//        list.data = arrayListOf(1376, 1488, 1600, 1712, 1824, 1936, 2048, 2160)
//        askRegisters(vv, list)
        //break
    }
    for (vv in archFull.emulators) {

    }
    for (vv in archFull.envValues) {

    }
    for (vv in archFull.views) {
        for (i in 0..3) {
            if (i == 1 || i == 2 || i == 3) continue
            val guiViewJSON = loadXMLArtifact(archFull.views[i].metaFile.ref!!.file.ref!!, "GUIView")
            view = format.decodeFromString<Meta2GUIView>(guiViewJSON)
        }

//        for (form in view.forms.list!!.guiForms) {
//            form.controls.list!!.fillValuesOfRegisters(equipment.registers.list)
//        }
    }

    //-------------------------------------------------------------------------------------------------
    val idForm = 1
    val arrayOfElements = renderViewGet(idForm)

    finalOut(
        arrayOfElements[0] as ArrayList<Meta2GUIFormButton>,
        arrayOfElements[1] as ArrayList<Meta2GUIFormButton>,
        arrayOfElements[2] as ArrayList<Meta2GUIFormButton>,
        idForm,false)
}

suspend fun selectGUI(id: Int){
    val format = Json {
        ignoreUnknownKeys = true
        isLenient = true
        //useArrayPolymorphism = true
    }
    val guiViewJSON = loadXMLArtifact(archFull.views[id].metaFile.ref!!.file.ref!!, "GUIView")
    view = format.decodeFromString<Meta2GUIView>(guiViewJSON)
}

fun renderViewGet(idForm: Int): ArrayList<Any> {
    val forms = view.forms.list!!.guiForms
    val form = forms[idForm]
    val level = form.level

    form.controls.list!!.fillValuesOfRegisters(equipment.registers.list)

    val navigationButtonsLevel1: ArrayList<Meta2GUIFormButton>
    val navigationButtonsLevel0 = generateButtonsLevel0(forms)

    var parentLevel3 = ""
    if (level == 3) parentLevel3 = form.parentName
    else if (level == 2) parentLevel3 = form.title
    val navigationButtonsLevel2 = generateButtonsLevel2(parentLevel3, forms)

    if (level >= 2) {
        var parentName = ""
        for (i in 0 until forms.size) {
            val form1 = forms[i]
            if (form1.title == parentLevel3) {
                parentName = form1.parentName
                break
            }
        }
        navigationButtonsLevel1 = generateButtonsLevel1(parentName, forms)
    }
    else navigationButtonsLevel1 = generateButtonsLevel1(form.title, forms)

    signUpRegTool(form)

    return arrayListOf(
        navigationButtonsLevel0,
        navigationButtonsLevel1,
        navigationButtonsLevel2,
        idForm, false
    )
}

fun signUpRegTool(form: Meta2GUIForm){
    guiList.clear()
    regTool = RenderingWithRegisters(archFull, form)
    val wrapperCollection = Meta2GUICollection()
    wrapperCollection.list = regTool!!.form.controls.list
    val idx = arrayListOf(0, 0, 0, 0)
    regTool!!.mappingRegisters(wrapperCollection, 0, 0, 0, idx)
}

suspend fun generateRegValues(){
    regTool!!.puttingRegisters()
}

suspend fun askRegisters(device: ESS2Device, list: UnitRegisterList) : ArrayList<Int>{
    val jsonList = Json.encodeToString(list)
    //println(jsonList)
    val values = api2.readESS2RegistersValues(token, "BMS/TCP", jsonList)
    return values.data!!
}

fun createButton(title: String, x: Int, y: Int, idForm: Int, level: Int): Meta2GUIFormButton {
    class TempClass(override var selectedForm: Int, override var onSelectForm: (Int) -> Unit) : FormButtonProps
    val button = Meta2GUIFormButton(TempClass(-1, {}))
    button.title = title
    button.x = x
    button.y = y
    button.idForm = idForm
    button.level = level
    return button
}

fun generateButtonsLevel0(forms: ArrayList<Meta2GUIForm>): ArrayList<Meta2GUIFormButton> {
    val buttons = ArrayList<Meta2GUIFormButton>()
    var offsetX = 50
    val offsetY = 5

    for (i in 0 until forms.size) {
        val form = forms[i]
        if (form.parentName == "Главный" && form.title != "") {
            buttons.add(createButton(form.title, offsetX, offsetY, i, form.level))
            offsetX += 30
        }
    }
    return buttons
}

fun generateButtonsLevel1(parentFormName: String, forms: ArrayList<Meta2GUIForm>): ArrayList<Meta2GUIFormButton> {
    val buttons = ArrayList<Meta2GUIFormButton>()
    var offsetX = 50
    val offsetY = 10

    for (i in 0 until forms.size) {
        val form = forms[i]
        if (form.parentName == parentFormName && form.title != "") {
            buttons.add(createButton(form.title, offsetX, offsetY, i, form.level))
            offsetX += 30
        }
    }
    return buttons
}

fun generateButtonsLevel2(parentFormName: String, forms: ArrayList<Meta2GUIForm>): ArrayList<Meta2GUIFormButton> {
    val buttons = ArrayList<Meta2GUIFormButton>()
    var offsetX = 50
    val offsetY = 20

    for (i in 0 until forms.size) {
        val form = forms[i]
        if (form.parentName == parentFormName && form.title != "" && form.title != "Главный") {
            buttons.add(createButton(form.title, offsetX, offsetY, i, form.level))
            offsetX += 30
        }
    }
    return buttons
}

fun toOneWord(data: ArrayList<Int>): Long {
    var out: Long = 0
    for (i in 0 until minOf(4, data.size)) {
        out = out or ((data[i].toLong() and 0x0FFFF) shl (i * 16))
    }
    return out
}