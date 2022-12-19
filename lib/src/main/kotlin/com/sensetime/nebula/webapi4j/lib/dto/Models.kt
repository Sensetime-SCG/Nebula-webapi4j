package com.sensetime.nebula.webapi4j.lib.dto

data class ResponseToken(
    val token: String
)

data class ResponseErrors(
    val code: Int,
    val msg: String
)

data class ResponseItemsInt(
    val items: List<Long>
)

data class RequestAuth(
    val username: String,
    val password: String,
    val validTime: Short
)
