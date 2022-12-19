package com.sensetime.nebula.webapi4j.lib.api

import okhttp3.MultipartBody
import javax.lang.model.type.NullType
import com.sensetime.nebula.webapi4j.lib.dto.*
import com.sensetime.nebula.webapi4j.lib.base.*

typealias RemoteResponseWrap<T> = RemoteResponse<T, Exception>

interface RemoteDataSource {
    suspend fun authLogin(body: RequestAuth): RemoteResponseWrap<ResponseToken>
    suspend fun authErrors(): RemoteResponseWrap<List<ResponseErrors>>

    suspend fun userCreate(body: RequestUser): RemoteResponseWrap<ResponseUser>

    suspend fun userSearch(id: Long): RemoteResponseWrap<ResponseUser>

    suspend fun userUpdate(id: Long, body: RequestUser): RemoteResponseWrap<ResponseUser>

    suspend fun userDelete(id: Long): RemoteResponse<NullType?, Exception>

    suspend fun users(offset: Long, limit: Long): RemoteResponseWrap<ResponseUserItems>

    suspend fun groupCreate(body:RequestGroup): RemoteResponseWrap<ResponseGroup>

    suspend fun groupSearch(id: Long):RemoteResponseWrap<ResponseGroup>

    suspend fun groupUpdate(id:Long, body: RequestGroup):RemoteResponseWrap<ResponseGroup>

    suspend fun groupDelete(id: Long): RemoteResponse<NullType?, Exception>

    suspend fun groups(offset: Long,limit: Long):RemoteResponseWrap<ResponseGroupItems>

    suspend fun groupWithUsers(id: Long):RemoteResponseWrap<ResponseItemsInt>

    suspend fun ruleCreate(body: RequestRule):RemoteResponseWrap<ResponseRule>

    suspend fun ruleSearch(id: Long):RemoteResponseWrap<ResponseRule>

    suspend fun ruleUpdate(id: Long, body: RequestRule): RemoteResponseWrap<ResponseRule>

    suspend fun ruleDelete(id: Long): RemoteResponse<NullType?, Exception>

    suspend fun rules(offset: Long,limit: Long):RemoteResponseWrap<ResponseRuleItems>

    suspend fun ruleWithGroup(id: Long):RemoteResponseWrap<ResponseItemsInt>

    suspend fun deviceReboot(): RemoteResponse<NullType?, Exception>

    suspend fun deviceRestore(): RemoteResponse<NullType?, Exception>

    suspend fun deviceInfo():RemoteResponseWrap<DeviceInfo>

    suspend fun deviceUpgrade(part:MultipartBody.Part): RemoteResponse<NullType?, Exception>

    suspend fun deviceRecognizeConfigGet():RemoteResponseWrap<DeviceRecognize>

    suspend fun deviceRecognizeConfigSet(body:DeviceRecognize): RemoteResponse<NullType?, Exception>

    suspend fun deviceAccessConfigGet():RemoteResponseWrap<DeviceAccess>

    suspend fun deviceAccessConfigSet(body: DeviceAccess): RemoteResponse<NullType?, Exception>

    suspend fun deviceFunctionsConfigGet():RemoteResponseWrap<DeviceFunctions>

    suspend fun deviceFunctionsConfigSet(body: DeviceFunctions): RemoteResponse<NullType?, Exception>

    suspend fun deviceSystemConfigSet(body: DeviceSystem): RemoteResponse<NullType?, Exception>

    suspend fun deviceSystemConfigGet():RemoteResponseWrap<DeviceSystem>

    suspend fun deviceCustomConfigSet(body: DeviceCustom): RemoteResponse<NullType?, Exception>

    suspend fun deviceCustomConfigGet():RemoteResponseWrap<DeviceCustom>

    suspend fun deviceTimeConfigSet(body: DeviceTime): RemoteResponse<NullType?, Exception>

    suspend fun deviceTimeConfigGet():RemoteResponseWrap<DeviceTime>

    suspend fun deviceDoorOpen(body:DeviceDoor): RemoteResponse<NullType?, Exception>

    suspend fun aiRecognitionQuality(part:MultipartBody.Part):RemoteResponseWrap<List<AiQuality>>

    suspend fun aiFeature(part:MultipartBody.Part):RemoteResponseWrap<String>

}