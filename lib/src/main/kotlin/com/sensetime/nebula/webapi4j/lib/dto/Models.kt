package com.sensetime.nebula.webapi4j.lib.dto

data class ResponseToken(
    val token: String
)

data class ResponseErrors(
    val code: Int,
    val msg: String
)

data class ResponseItems<T>(
    val items: List<T>
)

data class ResponseOffsetItems<T>(
    val offset:Short,
    val limit:Short,
    val count:Int,
    val total:Int,
    val items:List<T>
)

data class RequestAuth(
    val username: String,
    val password: String,
    val validTime: Short
)

data class RequestSearchOfName(
    val name: String
)