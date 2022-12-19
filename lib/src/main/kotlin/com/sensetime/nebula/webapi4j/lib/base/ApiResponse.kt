package com.sensetime.nebula.webapi4j.lib.base

import com.google.gson.annotations.SerializedName
import javax.lang.model.type.NullType


class ApiResponse<T>(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("data")
    val data: T?
)

data class BrandError(
    val code: Long,
    val msg: String,
    val data: NullType
)