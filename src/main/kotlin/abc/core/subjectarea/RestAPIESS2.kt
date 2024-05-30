package abc.core.subjectarea

import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.Headers
import org.w3c.fetch.RequestInit

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import abc.core.subjectarea.Artifact
import abc.core.subjectarea.EntityLink
import org.w3c.fetch.Body

class RestAPIESS2 (var ip: String = "127.0.0.1", var port: Int = 4567){
    suspend fun getEnvValues(SessionToken:String) : R<ESS2EnvValuesList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/envvalues/get",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ESS2EnvValuesList>(res2))
    }
    suspend fun metaDataCancel(SessionToken:String,pass:String) : R<CallResult> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/meta/cancel?pass="+pass+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<CallResult>(res2))
    }
    suspend fun metaDataDeploy(SessionToken:String,pass:String,id:Long) : R<CallResult> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/meta/deploy?pass="+pass+"&id="+id+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<CallResult>(res2))
    }
    suspend fun importMetaData(SessionToken:String,pass:String,id:Long) : R<ArrayList<OidString>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/meta/import?pass="+pass+"&id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<OidString>>(res2))
    }
    suspend fun loadMetaData(SessionToken:String,pass:String,id:Long) : R<OidString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/meta/load?pass="+pass+"&id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<OidString>(res2))
    }
    suspend fun importMetaData2(SessionToken:String,pass:String,id:Long) : R<ArrayList<OidString>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/meta/import2?pass="+pass+"&id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<OidString>>(res2))
    }
    suspend fun metaDataTest(SessionToken:String,pass:String,id:Long) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/meta/test?pass="+pass+"&id="+id+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun refreshStreamDataRegisters(SessionToken:String,nodeid:Long) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/streamregisters/refresh?nodeid="+nodeid+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun removeSettingsConfig(SessionToken:String,id:Long) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/config/remove?id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun importMetaDataXls(SessionToken:String,pass:String,id:Long) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/meta/import?pass="+pass+"&id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun iec61850ServerState(SessionToken:String) : R<JInt> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/iec61850/server/state",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JInt>(res2))
    }
    suspend fun disconnectFromPLM(SessionToken:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/plm/disconnect",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun getNewStreamPeriod(SessionToken:String,lastid:Long) : R<EntityList<ArchStreamPeriod>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/streamperiod/new/list?lastid="+lastid+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<EntityList<ArchStreamPeriod>>(res2))
    }
    suspend fun iec60870ServerState(SessionToken:String) : R<JInt> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/iec60870/server/state",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JInt>(res2))
    }
    suspend fun addSettingsConfig(SessionToken:String,title:String,comment:String) : R<ErrorList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/config/add?title="+title+"&comment="+comment+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ErrorList>(res2))
    }
    suspend fun deploySettingsConfig(SessionToken:String,id:Long) : R<ErrorList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/config/deploy?id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ErrorList>(res2))
    }
    suspend fun getCurrentMetaData(SessionToken:String) : R<MetaExternalSystem> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/metadata/get",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<MetaExternalSystem>(res2))
    }
    suspend fun readRegistersValues0(SessionToken:String) : R<IntegerList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/registers/values/read",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<IntegerList>(res2))
    }
    suspend fun readESS2SnapShotValues(SessionToken:String,deviceName:String) : R<IntegerList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/snapshot/list/read?deviceName="+deviceName+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<IntegerList>(res2))
    }
    suspend fun readRegisterValue(SessionToken:String,regnum:Int) : R<JInt> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/register/value/read?regnum="+regnum+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JInt>(res2))
    }
    suspend fun writeRegisterValue(SessionToken:String,regnum:Int,value:Int) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/register/value/write?regnum="+regnum+"&value="+value+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun readESS2RegistersValues(SessionToken:String,deviceName:String, body: String) : R<ArrayList<Int>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/registers/list/read?deviceName="+deviceName+"",RequestInit("post",headers, body))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<Int>>(res2))
    }
    suspend fun readRegistersValues(SessionToken:String) : R<IntegerList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/registers/list/read",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<IntegerList>(res2))
    }
    suspend fun readESS2NodeSnapShotValues(SessionToken:String,nodeid:Long,deviceName:String) : R<ArrayList<Int>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/node/snapshot/list/read?nodeid="+nodeid+"&deviceName="+deviceName+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<Int>>(res2))
    }
    suspend fun iec61850ServerOnOff(SessionToken:String,equipId:Long) : R<CallResult> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/iec61850/server/onoff?equipId="+equipId+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<CallResult>(res2))
    }
    suspend fun iec60870ServerOnOff(SessionToken:String) : R<CallResult> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/iec60870/server/onoff",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<CallResult>(res2))
    }
    suspend fun disconnectFromEquipment(SessionToken:String) : R<CallResult> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/equip/disconnect",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<CallResult>(res2))
    }
    suspend fun writeESS2SettingValue(SessionToken:String,deviceName:String,unit:Int,regnum:Int,value:Int) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/setting/write?deviceName="+deviceName+"&unit="+unit+"&regnum="+regnum+"&value="+value+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun metaDataDeployForce(SessionToken:String,pass:String,id:Long,force:Boolean) : R<CallResult> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/meta/deploy/forced?pass="+pass+"&id="+id+"&force="+force+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<CallResult>(res2))
    }
    suspend fun writeESS2RegisterValue(SessionToken:String,deviceName:String,unit:Int,regnum:Int,value:Int) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/register/value/write?deviceName="+deviceName+"&unit="+unit+"&regnum="+regnum+"&value="+value+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun connectToEquipmentForce(SessionToken:String,force:Boolean) : R<CallResult> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/equip/connect/forced?force="+force+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<CallResult>(res2))
    }
    suspend fun writeESS2CommandValue(SessionToken:String,deviceName:String,unit:Int,regnum:Int,value:Int) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/command/write?deviceName="+deviceName+"&unit="+unit+"&regnum="+regnum+"&value="+value+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun getArchitectureState(SessionToken:String) : R<ArrayList<Long>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/equip/state",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<Long>>(res2))
    }
    suspend fun readESS2RegisterValue(SessionToken:String,deviceName:String,unit:Int,regnum:Int) : R<JInt> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/register/value/read?deviceName="+deviceName+"&unit="+unit+"&regnum="+regnum+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JInt>(res2))
    }
    suspend fun connectToEquipment(SessionToken:String) : R<CallResult> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/equip/connect",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<CallResult>(res2))
    }
    suspend fun profilerOnOff(SessionToken:String) : R<CallResult> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/profiler/onoff",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<CallResult>(res2))
    }
    suspend fun quitFailure(SessionToken:String,setting:Boolean,equipName:String,logUnit:Int,regnum:Int,bitnum:Int) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/failure/quit?setting="+setting+"&equipName="+equipName+"&logUnit="+logUnit+"&regnum="+regnum+"&bitnum="+bitnum+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun connectToPLM(SessionToken:String,id:Long) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/plm/connect?id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun getStreamPeriod(SessionToken:String,nodeid:Long,timeInMS1:Long,timeInMS2:Long,level:Int) : R<EntityList<ArchStreamPeriod>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/streamperiod/list?nodeid="+nodeid+"&timeInMS1="+timeInMS1+"&timeInMS2="+timeInMS2+"&level="+level+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<EntityList<ArchStreamPeriod>>(res2))
    }
    suspend fun isPLMReady(SessionToken:String) : R<JBoolean> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/plm/ready",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JBoolean>(res2))
    }
    suspend fun addConfig(SessionToken:String,title:String,comment:String) : R<JLong> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/config/add?title="+title+"&comment="+comment+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JLong>(res2))
    }
    suspend fun sendEnvValues() : R<JEmpty> {
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/envvalues/send",RequestInit("post"))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun getEnvValuesNode(SessionToken:String,nodeid:Long) : R<ESS2EnvValuesList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/node/envvalues/get?nodeid="+nodeid+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ESS2EnvValuesList>(res2))
    }
    suspend fun clockCycle(type:Int) : R<JEmpty> {
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/clock?type="+type+"",RequestInit("get"))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun keepalive(onoff:Boolean) : R<JEmpty> {
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/keepalive?onoff="+onoff+"",RequestInit("post"))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun quitAllFailures(SessionToken:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/failure/quit/all",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun sendStreamData() : R<JEmpty> {
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/streamdata/send",RequestInit("post"))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun sendSnapShot() : R<JEmpty> {
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/snapshot/send",RequestInit("post"))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun getNewStreamData(SessionToken:String,lastid:Long,periodid:Long) : R<EntityList<ArchStreamDataSet>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/streamdata/new/list?lastid="+lastid+"&periodid="+periodid+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<EntityList<ArchStreamDataSet>>(res2))
    }
    suspend fun getOwnAccount(SessionToken:String) : R<Account> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/user/account/get",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Account>(res2))
    }
    suspend fun reloadMetaData(SessionToken:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/meta/reload",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun getStreamData2(SessionToken:String,mode:Int,logNum:Int,timeInMS1:Long,timeInMS2:Long) : R<Pair<ErrorList,ArrayList<StreamDataValue>>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/streamdata2/get?mode="+mode+"&logNum="+logNum+"&timeInMS1="+timeInMS1+"&timeInMS2="+timeInMS2+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Pair<ErrorList,ArrayList<StreamDataValue>>>(res2))
    }
    suspend fun setConfig(SessionToken:String,id:Long) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/config/set?id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun getStreamData(SessionToken:String,mode:Int,logNum:Int,timeInMS1:Long,timeInMS2:Long) : R<ArrayList<StreamDataValue>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/streamdata/get?mode="+mode+"&logNum="+logNum+"&timeInMS1="+timeInMS1+"&timeInMS2="+timeInMS2+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<StreamDataValue>>(res2))
    }
    suspend fun profilerExport(SessionToken:String) : R<CallResult2> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/profiler/export",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<CallResult2>(res2))
    }
    suspend fun getConfigList(SessionToken:String) : R<ArrayList<PLMConfig>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/config/list",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<PLMConfig>>(res2))
    }
    suspend fun removeConfig(SessionToken:String,id:Long) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/config/remove?id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun profilerState(SessionToken:String) : R<JInt> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/profiler/state",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JInt>(res2))
    }
    suspend fun execScript(SessionToken:String,id:Long,trace:Boolean) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess2/script/exec?id="+id+"&trace="+trace+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun writeSettingValue(SessionToken:String,regnum:Int,value:Int) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/setting/write?regnum="+regnum+"&value="+value+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun writeCommandValue(SessionToken:String,regnum:Int,value:Int) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/ess/command/write?regnum="+regnum+"&value="+value+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
}
