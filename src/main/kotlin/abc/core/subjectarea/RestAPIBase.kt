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
import org.w3c.xhr.XMLHttpRequest

class RestAPIBase (var ip: String = "127.0.0.1", var port: Int = 4567){
    suspend fun shutdown(SessionToken:String,pass:String) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/shutdown?pass="+pass+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun lock(SessionToken:String,pass:String,on:Boolean) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/lock?pass="+pass+"&on="+on+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun update(SessionToken:String,artId:Long) : R<Artifact> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/file/update?artId="+artId+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Artifact>(res2))
    }
    suspend fun execute(SessionToken:String,pass:String,cmd:String) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/execute?pass="+pass+"&cmd="+cmd+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun debugToken(pass:String) : R<JString> {
        val res = window
        .fetch("http://"+ip+":"+port+"/api/debug/token?pass="+pass+"",RequestInit("get"))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun getEntity(SessionToken:String,classname:String,id:Long,level:Int) : R<DBRequest> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/get?classname="+classname+"&id="+id+"&level="+level+"",
            RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<DBRequest>(res2))
    }
    suspend fun workSettings(SessionToken:String) : R<DBRequest> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/worksettings",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<DBRequest>(res2))
    }
    suspend fun login(phone:String,pass:String) : R<User> {
        val res = window
        .fetch("http://"+ip+":"+port+"/api/user/login/phone?phone="+phone+"&pass="+pass+"",
            RequestInit("get"))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<User>(res2))
    }
    suspend fun login() : R<User> {
        val res = window
        .fetch("http://"+ip+":"+port+"/api/user/login",RequestInit("post"))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<User>(res2))
    }
    suspend fun logoff(SessionToken:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/user/logoff",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun getEntityList(SessionToken:String,classname:String,mode:Int,level:Int) : R<ArrayList<DBRequest>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/list?classname="+classname+"&mode="+mode+"&level="+level+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<DBRequest>>(res2))
    }
    suspend fun downLoadByName(SessionToken:String,fname:String,root:Boolean) : R<ResponseBody> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/file/loadByName?fname="+fname+"&root="+root+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ResponseBody>(res2))
    }
    suspend fun downLoad(SessionToken:String,id:Long) : R<ResponseBody> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/file/load?id="+id+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ResponseBody>(res2))
    }
    suspend fun downLoadAsString(SessionToken:String,id:Long) : R<String> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
            .fetch("http://"+ip+":"+port+"/api/file/load?id="+id+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        return R("", res2)
    }
    suspend fun keepalive(SessionToken:String) : R<JInt> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/keepalive",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JInt>(res2))
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
    suspend fun updateEntity(SessionToken:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/update",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun setAddressGPS(SessionToken:String,id:Long) : R<JLong> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/address/setgps?id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JLong>(res2))
    }
    suspend fun clearTable(SessionToken:String,table:String,pass:String) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/cleartable?table="+table+"&pass="+pass+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun getConsoleLog(SessionToken:String,count:Int) : R<StringList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/debug/consolelog?count="+count+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<StringList>(res2))
    }
    suspend fun getBugList(SessionToken:String,mode:Int) : R<EntityList<BugMessage>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/bug/list?mode="+mode+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<EntityList<BugMessage>>(res2))
    }
    suspend fun removeEntity(SessionToken:String,classname:String,id:Long) : R<JBoolean> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/remove?classname="+classname+"&id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JBoolean>(res2))
    }
    suspend fun sendBug(SessionToken:String) : R<JLong> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/bug/add",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JLong>(res2))
    }
    suspend fun getBug(SessionToken:String,id:Long) : R<BugMessage> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/bug/get?id="+id+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<BugMessage>(res2))
    }
    suspend fun getEntityNumber(SessionToken:String,classname:String) : R<JInt> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/number?classname="+classname+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JInt>(res2))
    }
    suspend fun clearDB(SessionToken:String,pass:String) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/cleardb?pass="+pass+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun exportDBxlsx(SessionToken:String,xlsx:Boolean,blocksize:Int) : R<Artifact> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/exportdb?xlsx="+xlsx+"&blocksize="+blocksize+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Artifact>(res2))
    }
    suspend fun exportDBxls(SessionToken:String) : R<Artifact> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/exportdb",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Artifact>(res2))
    }
    suspend fun dump(SessionToken:String) : R<Artifact> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/dump",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Artifact>(res2))
    }
    suspend fun ping() : R<JEmpty> {
        val res = window
        .fetch("http://"+ip+":"+port+"/api/debug/ping",RequestInit("get"))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun rebootServer(SessionToken:String,pass:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/reboot?pass="+pass+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun addEntity(SessionToken:String,level:Int) : R<JLong> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/add?level="+level+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JLong>(res2))
    }
    suspend fun getFolder(SessionToken:String,pass:String,folder:String) : R<StringList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/files/list?pass="+pass+"&folder="+folder+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<StringList>(res2))
    }
    suspend fun copyDBTo(SessionToken:String,pass:String,port:Int) : R<ErrorList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/copydbto?pass="+pass+"&port="+port+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ErrorList>(res2))
    }
    suspend fun getConstAll(SessionToken:String) : R<ArrayList<ConstValue>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/const/all",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<ConstValue>>(res2))
    }
    suspend fun getArtifactById(SessionToken:String,id:Long,level:Int) : R<Artifact> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/artifact/get?id="+id+"&level="+level+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Artifact>(res2))
    }
    suspend fun setCashMode(SessionToken:String,mode:Boolean,pass:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/cashmode?mode="+mode+"&pass="+pass+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun prepareDB(SessionToken:String,operation:Int,pass:String) : R<ErrorList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/preparedb?operation="+operation+"&pass="+pass+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ErrorList>(res2))
    }
    suspend fun undeleteById(SessionToken:String,entity:String,id:Long) : R<JBoolean> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/undelete?entity="+entity+"&id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JBoolean>(res2))
    }
    suspend fun longPolling(SessionToken:String,pass:String) : R<ErrorList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/longpoll?pass="+pass+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ErrorList>(res2))
    }
    suspend fun upload(SessionToken:String,description:String,origname:String) : R<Artifact> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/file/upload?description="+description+"&origname="+origname+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Artifact>(res2))
    }
    suspend fun updateUser(SessionToken:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/user/update",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun importDBxls(SessionToken:String,pass:String,id:Long) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/importdb?pass="+pass+"&id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun deleteById(SessionToken:String,entity:String,id:Long) : R<JBoolean> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/delete?entity="+entity+"&id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JBoolean>(res2))
    }
    suspend fun getUserList(SessionToken:String,mode:Int,level:Int) : R<EntityList<User>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/user/list?mode="+mode+"&level="+level+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<EntityList<User>>(res2))
    }
    suspend fun addUser(SessionToken:String) : R<JLong> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/user/add",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JLong>(res2))
    }
    suspend fun serverState(SessionToken:String) : R<ServerState> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/serverstate",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ServerState>(res2))
    }
    suspend fun setArtifactName(SessionToken:String,id:Long,name:String) : R<Artifact> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/artifact/setname?id="+id+"&name="+name+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Artifact>(res2))
    }
    suspend fun createArtifact(SessionToken:String,description:String,origname:String,filesize:Long) : R<Artifact> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/artifact/create?description="+description+"&origname="+origname+"&filesize="+filesize+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Artifact>(res2))
    }
    suspend fun convertArtifact(SessionToken:String,id:Long) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/artifact/convert?id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun deploy(SessionToken:String,pass:String) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/deploy?pass="+pass+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun reopenLogFile(SessionToken:String,pass:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/logfile/reopen?pass="+pass+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun currentVersion(SessionToken:String) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/version",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun getConstByGroups(SessionToken:String) : R<ArrayList<ArrayList<ConstValue>>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/const/bygroups",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<ArrayList<ConstValue>>>(res2))
    }
    suspend fun copyDBFrom(SessionToken:String,pass:String,port:Int) : R<ErrorList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/copydbfrom?pass="+pass+"&port="+port+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ErrorList>(res2))
    }
    suspend fun getArtifactList(SessionToken:String,mode:Int,level:Int) : R<ArtifactList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/artifact/list?mode="+mode+"&level="+level+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArtifactList>(res2))
    }
    suspend fun getGPSByAddress(SessionToken:String) : R<GPSPoint> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/gps/address",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<GPSPoint>(res2))
    }
    suspend fun getUserById(SessionToken:String,id:Long,level:Int) : R<User> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/user/get?id="+id+"&level="+level+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<User>(res2))
    }
    suspend fun replaceArtifact(SessionToken:String,classname:String,fieldname:String,id:Long,artifactid:Long) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/artifact/replace?classname="+classname+"&fieldname="+fieldname+"&id="+id+"&artifactid="+artifactid+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun deployMB(SessionToken:String,pass:String,mb:Int) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/deploy?pass="+pass+"&mb="+mb+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun removeArtifact(SessionToken:String,id:Long) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/artifact/remove?id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun getHelpFileList(question:String) : R<EntityList<HelpFile>> {
        val res = window
        .fetch("http://"+ip+":"+port+"/api/helpfile/list?question="+question+"",RequestInit("get"))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<EntityList<HelpFile>>(res2))
    }
    suspend fun uploadByName(SessionToken:String,fname:String,root:Boolean) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/file/uploadByName?fname="+fname+"&root="+root+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun restore(SessionToken:String,pass:String,id:Long) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/restore?pass="+pass+"&id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun testCall(SessionToken:String,operation:Int,value:String) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/admin/testcall?operation="+operation+"&value="+value+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun downLoad2(SessionToken:String) : R<ResponseBody> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/file/load2",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ResponseBody>(res2))
    }
    suspend fun getNotification(SessionToken:String,id:Long) : R<NTMessage> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/notification/get?id="+id+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<NTMessage>(res2))
    }
    suspend fun addNotification(SessionToken:String) : R<JLong> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/notification/add",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JLong>(res2))
    }
    suspend fun addArtifactToList(SessionToken:String,classname:String,fieldname:String,id:Long,artifactid:Long) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/artifactlist/add?classname="+classname+"&fieldname="+fieldname+"&id="+id+"&artifactid="+artifactid+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun removeNotification(SessionToken:String,id:Long) : R<JBoolean> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/notification/remove?id="+id+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JBoolean>(res2))
    }
    suspend fun getNotificationCount(SessionToken:String) : R<JInt> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/notification/count",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JInt>(res2))
    }
    suspend fun updateNotification(SessionToken:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/notification/update",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun createArtifactFromString(SessionToken:String,fileName:String,text:String) : R<Artifact> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/artifact/fromstring?fileName="+fileName+"&text="+text+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Artifact>(res2))
    }
    suspend fun removeArtifactFromList(SessionToken:String,classname:String,fieldname:String,id:Long,artifactid:Long) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/artifactlist/remove?classname="+classname+"&fieldname="+fieldname+"&id="+id+"&artifactid="+artifactid+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun setNotificationState(SessionToken:String,id:Long,state:Int) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/notification/setstate?id="+id+"&state="+state+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun addNotificationBroadcast(SessionToken:String) : R<JInt> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/notification/add/broadcast",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JInt>(res2))
    }
    suspend fun getNotificationList(SessionToken:String) : R<EntityList<NTMessage>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/notification/list",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<EntityList<NTMessage>>(res2))
    }
    suspend fun getArtifactConditionList(SessionToken:String,type:Int,owner:String,namemask:String,filenamemask:String,size1:Long,size2:Long,dateInMS1:Long,dateInMS2:Long) : R<ArtifactList> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/artifact/condition/list?type="+type+"&owner="+owner+"&namemask="+namemask+"&filenamemask="+filenamemask+"&size1="+size1+"&size2="+size2+"&dateInMS1="+dateInMS1+"&dateInMS2="+dateInMS2+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArtifactList>(res2))
    }
    suspend fun getNotificationUserList(SessionToken:String,userid:Long,usertype:Int,state:Int) : R<EntityList<NTMessage>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/notification/user/list?userid="+userid+"&usertype="+usertype+"&state="+state+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<EntityList<NTMessage>>(res2))
    }
    suspend fun updateEntityField(SessionToken:String,name:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/update/field?name="+name+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun getWorkSettingsString(SessionToken:String,field:String) : R<JString> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/worksettings/get/string?field="+field+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JString>(res2))
    }
    suspend fun getWorkSettingsInt(SessionToken:String,field:String) : R<JInt> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/worksettings/get/int?field="+field+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JInt>(res2))
    }
    suspend fun getWorkSettingsBoolean(SessionToken:String,field:String) : R<JBoolean> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/worksettings/get/boolean?field="+field+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JBoolean>(res2))
    }
    suspend fun updateWorkSettings(SessionToken:String,field:String,value:Boolean) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/worksettings/update/boolean?field="+field+"&value="+value+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun updateWorkSettings(SessionToken:String,field:String,value:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/worksettings/update/string?field="+field+"&value="+value+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun updateWorkSettings(SessionToken:String,field:String,value:Int) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/worksettings/update/int?field="+field+"&value="+value+"",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun updateWorkSettings(SessionToken:String) : R<JEmpty> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/worksettings/update",RequestInit("post",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<JEmpty>(res2))
    }
    suspend fun getEntityListWithPaths(SessionToken:String,classname:String,mode:Int,level:Int,paths:String) : R<ArrayList<DBRequest>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/list/withpaths?classname="+classname+"&mode="+mode+"&level="+level+"&paths="+paths+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<DBRequest>>(res2))
    }
    suspend fun getNamesByPattern(SessionToken:String,entity:String,pattern:String) : R<EntityList<EntityNamed>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/names/get?entity="+entity+"&pattern="+pattern+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<EntityList<EntityNamed>>(res2))
    }
    suspend fun getSetverEnvironment(SessionToken:String) : R<ArrayList<String>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/user/server/environment",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<String>>(res2))
    }
    suspend fun getEntityListByQuery(SessionToken:String,classname:String,xmlquery:String,level:Int) : R<ArrayList<DBRequest>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/list/query?classname="+classname+"&xmlquery="+xmlquery+"&level="+level+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<DBRequest>>(res2))
    }
    suspend fun getEntityListLast(SessionToken:String,classname:String,number:Int,level:Int) : R<ArrayList<DBRequest>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/list/last?classname="+classname+"&number="+number+"&level="+level+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<ArrayList<DBRequest>>(res2))
    }
    suspend fun getConsoleLogPolling(SessionToken:String,lastnum:Long) : R<Pair<Long,StringList>> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/debug/consolelog/polling?lastnum="+lastnum+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<Pair<Long,StringList>>(res2))
    }
    suspend fun getEntityWithPaths(SessionToken:String,classname:String,id:Long,level:Int,paths:String) : R<DBRequest> {
        val headers = Headers()
        headers.append("SessionToken",SessionToken)
        val res = window
        .fetch("http://"+ip+":"+port+"/api/entity/get/withpaths?classname="+classname+"&id="+id+"&level="+level+"&paths="+paths+"",RequestInit("get",headers))
            .await()
        if (!res.ok)
            return R(res.statusText+" "+res.text().await(),null)
        val res2 = res.text().await()
        val format = Json { ignoreUnknownKeys = true }
        return R("",format.decodeFromString<DBRequest>(res2))
    }
}
