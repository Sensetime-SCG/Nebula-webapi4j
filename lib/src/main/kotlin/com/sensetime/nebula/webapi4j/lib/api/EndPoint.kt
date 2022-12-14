package com.sensetime.nebula.webapi4j.lib.api


import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import javax.lang.model.type.NullType
import com.sensetime.nebula.webapi4j.lib.base.*
import com.sensetime.nebula.webapi4j.lib.dto.*

interface EndPoint {
    @POST("v1/auth/login")
    suspend fun authLogin(@Body body: RequestAuth): Response<ApiResponse<ResponseToken>>

    @GET("v1/auth/errors")
    suspend fun authErrors(): Response<ApiResponse<List<ResponseErrors>>>

    @POST("v1/user")
    suspend fun userCreate(@Body body: RequestUser) :Response<ApiResponse<ResponseUser>>

    @GET("v1/user/id/{id}")
    suspend fun userSearch(@Path("id") id: Long): Response<ApiResponse<ResponseUser>>

    @PUT("v1/user/id/{id}")
    suspend fun userUpdate(@Path("id") id: Long, @Body body: RequestUser): Response<ApiResponse<ResponseUser>>

    @DELETE("v1/user/id/{id}")
    suspend fun userDelete(@Path("id") id: Long): Response<ApiResponse<NullType>>

    @GET("v1/user/offset/{offset}/limit/{limit}")
    suspend fun users(
        @Path("offset") offset: Long,
        @Path("limit") limit: Long
    ): Response<ApiResponse<ResponseUserItems>>

    @POST("v1/group")
    suspend fun groupCreate(@Body body: RequestGroup): Response<ApiResponse<ResponseGroup>>

    @GET("v1/group/id/{id}")
    suspend fun groupSearch(@Path("id") id: Long): Response<ApiResponse<ResponseGroup>>

    @PUT("v1/group/id/{id}")
    suspend fun groupUpdate(@Path("id") id: Long, @Body body: RequestGroup): Response<ApiResponse<ResponseGroup>>

    @DELETE("v1/group/id/{id}")
    suspend fun groupDelete(@Path("id") id: Long): Response<ApiResponse<NullType>>

    @GET("v1/group/offset/{offset}/limit/{limit}")
    suspend fun groups(
        @Path("offset") offset: Long,
        @Path("limit") limit: Long
    ): Response<ApiResponse<ResponseGroupItems>>

    @GET("v1/group/id/{id}/users")
    suspend fun groupWithUsers(@Path("id") id: Long): Response<ApiResponse<ResponseItemsInt>>

    @POST("v1/rule")
    suspend fun ruleCreate(@Body body: RequestRule): Response<ApiResponse<ResponseRule>>

    @GET("v1/rule/id/{id}")
    suspend fun ruleSearch(@Path("id") id: Long): Response<ApiResponse<ResponseRule>>

    @PUT("v1/rule/id/{id}")
    suspend fun ruleUpdate(@Path("id") id: Long, @Body body: RequestRule): Response<ApiResponse<ResponseRule>>

    @DELETE("v1/rule/id/{id}")
    suspend fun ruleDelete(@Path("id") id: Long): Response<ApiResponse<NullType>>

    @GET("v1/rule/offset/{offset}/limit/{limit}")
    suspend fun rules(
        @Path("offset") offset: Long,
        @Path("limit") limit: Long
    ): Response<ApiResponse<ResponseRuleItems>>

    @GET("v1/rule/id/{id}/groups")
    suspend fun ruleWithGroup(@Path("id") id: Long): Response<ApiResponse<ResponseItemsInt>>

    @GET("v1/device/reboot")
    suspend fun deviceReboot(): Response<ApiResponse<NullType>>

    @GET("v1/device/restore")
    suspend fun deviceRestore(): Response<ApiResponse<NullType>>

    @GET("v1/device/info")
    suspend fun deviceInfo(): Response<ApiResponse<DeviceInfo>>

    @Multipart
    @POST("v1/device/upgrade")
    suspend fun deviceUpgrade(@Part part: MultipartBody.Part): Response<ApiResponse<NullType>>

    @GET("v1/device/recognize")
    suspend fun deviceRecognizeConfigGet(): Response<ApiResponse<DeviceRecognize>>

    @POST("v1/device/recognize")
    suspend fun deviceRecognizeConfigSet(@Body body: DeviceRecognize): Response<ApiResponse<NullType>>

    @GET("v1/device/access")
    suspend fun deviceAccessConfigGet(): Response<ApiResponse<DeviceAccess>>

    @POST("v1/device/access")
    suspend fun deviceAccessConfigSet(@Body body: DeviceAccess): Response<ApiResponse<NullType>>

    @POST("v1/device/functions")
    suspend fun deviceFunctionsConfigSet(@Body body: DeviceFunctions): Response<ApiResponse<NullType>>

    @GET("v1/device/functions")
    suspend fun deviceFunctionsConfigGet(): Response<ApiResponse<DeviceFunctions>>

    @POST("v1/device/system")
    suspend fun deviceSystemConfigSet(@Body body: DeviceSystem): Response<ApiResponse<NullType>>

    @GET("v1/device/system")
    suspend fun deviceSystemConfigGet(): Response<ApiResponse<DeviceSystem>>

    @POST("v1/device/custom")
    suspend fun deviceCustomConfigSet(@Body body: DeviceCustom):Response<ApiResponse<NullType>>

    @GET("v1/device/custom")
    suspend fun deviceCustomConfigGet():Response<ApiResponse<DeviceCustom>>

    @POST("v1/device/time")
    suspend fun deviceTimeConfigSet(@Body body:DeviceTime):Response<ApiResponse<NullType>>

    @GET("v1/device/time")
    suspend fun deviceTimeConfigGet():Response<ApiResponse<DeviceTime>>

    @POST("v1/device/door")
    suspend fun deviceDoorOpen(@Body body:DeviceDoor):Response<ApiResponse<NullType>>

    @Multipart
    @POST("v1/ai/recognitionquality")
    suspend fun aiRecognitionQuality(@Part part: MultipartBody.Part):Response<ApiResponse<List<AiQuality>>>

    @Multipart
    @POST("v1/ai/feature")
    suspend fun aiFeature(@Part part: MultipartBody.Part):Response<ApiResponse<String>>
}