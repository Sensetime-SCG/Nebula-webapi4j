package com.sensetime.nebula.webapi4j.lib

import okhttp3.Interceptor
import okhttp3.Response

class InterceptorAuth(var refreshCallback: (() -> Unit)? = null) : Interceptor {
    val jwt: Jwt = Jwt
    var token: String = ""
        set

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!request.url().encodedPath().contains("v1/auth/")) {
            if (!jwt.verify(token)) {
                println("invoke, refresh token callback")
                //todo: token expire should notice
                refreshCallback?.invoke()
            }
            request = request.newBuilder().header("Authorization", "Bearer $token").build()
        }
        return chain.proceed(request)
    }
}