package com.sensetime.nebula.webapi4j.lib.base

sealed class WebApiError {
    data class General(val throwable: Throwable) : WebApiError()
}