package com.sensetime.nebula.webapi4j.lib.api

import com.sensetime.nebula.webapi4j.lib.base.*
import com.sensetime.nebula.webapi4j.lib.dto.*
import okhttp3.MultipartBody
import javax.lang.model.type.NullType

class RemoteDataSourceImpl(
    private val endPoint: EndPoint
) : RemoteDataSource {

    override suspend fun authLogin(body: RequestAuth): RemoteResponseWrap<ResponseToken> {
        return safeApiResponseCall(endPoint.authLogin(body))
    }

    override suspend fun authErrors(): RemoteResponseWrap<List<ResponseErrors>> {
        return safeApiResponseCall(response = endPoint.authErrors())
    }

    override suspend fun userCreate(body: RequestUser): RemoteResponseWrap<ResponseUser> {
        return safeApiResponseCall(endPoint.userCreate(body))
    }

    override suspend fun userGet(id: Long): RemoteResponseWrap<ResponseUser> {
        return safeApiResponseCall(endPoint.userGet(id))
    }

    override suspend fun userUpdate(id: Long, body: RequestUser): RemoteResponseWrap<ResponseUser> {
        return safeApiResponseCall(endPoint.userUpdate(id, body))
    }

    override suspend fun userDelete(id: Long): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.userDelete(id),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun users(offset: Long, limit: Long): RemoteResponseWrap<ResponseOffsetItems<ResponseUser>> {
        return safeApiResponseCall(endPoint.users(offset, limit))
    }

    override suspend fun groupCreate(body: RequestGroup): RemoteResponseWrap<ResponseGroup> {
        return safeApiResponseCall(endPoint.groupCreate(body))
    }

    override suspend fun groupGet(id: Long): RemoteResponseWrap<ResponseGroup> {
        return safeApiResponseCall(endPoint.groupGet(id))
    }

    override suspend fun groupUpdate(id: Long, body: RequestGroup): RemoteResponseWrap<ResponseGroup> {
        return safeApiResponseCall(endPoint.groupUpdate(id, body))
    }

    override suspend fun groupDelete(id: Long): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.groupDelete(id),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun groupWithUsers(id: Long): RemoteResponseWrap<ResponseItems<Long>> {
        return safeApiResponseCall(endPoint.groupWithUsers(id))
    }

    override suspend fun groups(offset: Long, limit: Long): RemoteResponseWrap<ResponseOffsetItems<ResponseGroup>> {
        return safeApiResponseCall(endPoint.groups(offset, limit))
    }

    override suspend fun ruleCreate(body: RequestRule): RemoteResponseWrap<ResponseRule> {
        return safeApiResponseCall(endPoint.ruleCreate(body))
    }

    override suspend fun ruleGet(id: Long): RemoteResponseWrap<ResponseRule> {
        return safeApiResponseCall(endPoint.ruleGet(id))
    }

    override suspend fun ruleUpdate(id: Long, body: RequestRule): RemoteResponseWrap<ResponseRule> {
        return safeApiResponseCall(endPoint.ruleUpdate(id, body))
    }

    override suspend fun ruleDelete(id: Long): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.ruleDelete(id),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun ruleWithGroup(id: Long): RemoteResponseWrap<ResponseItems<Long>> {
        return safeApiResponseCall(endPoint.ruleWithGroup(id))
    }

    override suspend fun rules(offset: Long, limit: Long): RemoteResponseWrap<ResponseOffsetItems<ResponseRule>> {
        return safeApiResponseCall(endPoint.rules(offset, limit))
    }

    override suspend fun deviceReboot(): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.deviceReboot(),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun deviceRestore(): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.deviceRestore(),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun deviceInfo(): RemoteResponseWrap<DeviceInfo> {
        return safeApiResponseCall(endPoint.deviceInfo())
    }

    override suspend fun deviceUpgrade(part: MultipartBody.Part): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.deviceUpgrade(part),
            bodyToRemote = { RemoteResponse.createSuccess(null) }, remoteError = { it.asException() })
    }

    override suspend fun deviceRecognizeConfigGet(): RemoteResponseWrap<DeviceRecognize> {
        return safeApiResponseCall(endPoint.deviceRecognizeConfigGet())
    }

    override suspend fun deviceRecognizeConfigSet(body: DeviceRecognize): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.deviceRecognizeConfigSet(body),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun deviceAccessConfigGet(): RemoteResponseWrap<DeviceAccess> {
        return safeApiResponseCall(endPoint.deviceAccessConfigGet())
    }

    override suspend fun deviceAccessConfigSet(body: DeviceAccess): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.deviceAccessConfigSet(body),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun deviceFunctionsConfigGet(): RemoteResponseWrap<DeviceFunctions> {
        return safeApiResponseCall(endPoint.deviceFunctionsConfigGet())
    }

    override suspend fun deviceFunctionsConfigSet(body: DeviceFunctions): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.deviceFunctionsConfigSet(body),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun deviceSystemConfigGet(): RemoteResponseWrap<DeviceSystem> {
        return safeApiResponseCall(endPoint.deviceSystemConfigGet())
    }

    override suspend fun deviceSystemConfigSet(body: DeviceSystem): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.deviceSystemConfigSet(body),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun deviceCustomConfigGet(): RemoteResponseWrap<DeviceCustom> {
        return safeApiResponseCall(endPoint.deviceCustomConfigGet())
    }

    override suspend fun deviceCustomConfigSet(body: DeviceCustom): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.deviceCustomConfigSet(body),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun deviceTimeConfigGet(): RemoteResponseWrap<DeviceTime> {
        return safeApiResponseCall(endPoint.deviceTimeConfigGet())
    }

    override suspend fun deviceTimeConfigSet(body: DeviceTime): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.deviceTimeConfigSet(body),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun deviceDoorOpen(body: DeviceDoor): RemoteResponse<NullType?, Exception> {
        return safeApiCallAllowEmptyBody(endPoint.deviceDoorOpen(body),
            { RemoteResponse.createSuccess(null) },
            { it.asException() })
    }

    override suspend fun aiRecognitionQuality(part: MultipartBody.Part): RemoteResponseWrap<List<AiQuality>> {
        return safeApiResponseCall(endPoint.aiRecognitionQuality(part))
    }

    override suspend fun aiFeature(part: MultipartBody.Part): RemoteResponseWrap<String> {
        return safeApiResponseCall(endPoint.aiFeature(part))
    }
}