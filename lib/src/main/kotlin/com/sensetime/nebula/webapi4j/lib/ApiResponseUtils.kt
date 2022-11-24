package com.sensetime.nebula.webapi4j.lib

import com.google.gson.Gson
import retrofit2.Response

fun <T, E> ApiResponse<T>.toRemoteResponse(
    remoteError: (RemoteError) -> E,
    brandError: (BrandError) -> E
): RemoteResponse<T, E> {
    val info = this.msg
    return if (this.code == 200) {
        if (this.data == null) {
            RemoteResponse.createError(remoteError(RemoteError.general(Exception("ApiResponse body is null!"))))
        }else{
            RemoteResponse.createSuccess(this.data)
        }
    } else {
        if (info == null) {
            RemoteResponse.createError(remoteError(RemoteError.general(Exception("ApiResponse error is null, and result =${this.code}"))))
        }else {
            RemoteResponse.createError(remoteError(RemoteError.general(Exception(info))))
        }
    }
}

fun <T, R, E> Response<T>.toRemoteResponseAllowEmptyBody(
    bodyToRemote: (T?) -> RemoteResponse<R?, E>,
    remoteError: (error: RemoteError) -> E
): RemoteResponse<R?, E> {
    return if (isSuccessful) {
        val body = body()
        if (body == null) {
            RemoteResponse.createSuccess<R?, E>(null)
        } else {
            bodyToRemote(body)
        }
    } else {
        val errBody = errorBody()?.string()
        if(errBody!=null) {
            RemoteResponse.createError(remoteError(RemoteError.general(Exception("code: " + code() + ", msg: " + errBody))))
        } else{
            RemoteResponse.createError(remoteError(RemoteError.general(Exception("code: " + code()))))
        }
    }
}

fun <T, R, E> Response<T>.toRemoteResponse(
    bodyToRemote: (T) -> RemoteResponse<R, E>,
    remoteError: (error: RemoteError) -> E
): RemoteResponse<R, E> {
    val body = body()
    return if (body != null) {
        bodyToRemote(body)
    } else {
        val errBody = errorBody()?.string()
        if(errBody!=null){
            val data = Gson().fromJson(errBody, BrandError::class.java)
            RemoteResponse.createError(remoteError(RemoteError.general(Exception(data.msg))))
        }else
            RemoteResponse.createError(remoteError(RemoteError.general(Exception("empty response,code: ${code()}"))))
    }
}



fun <T, R, E> safeApiCall(
    response: Response<T>,
    bodyToRemote: (T) -> RemoteResponse<R, E>,
    remoteError: (RemoteError) -> E
): RemoteResponse<R, E> {
    return try {
        response.toRemoteResponse(bodyToRemote, remoteError)
    } catch (e: Exception) {
        RemoteResponse.createError(remoteError(RemoteError.General(e)))
    }
}


fun <T, E> safeApiResponseCall(
    response: Response<ApiResponse<T>>,
    remoteError: (RemoteError) -> E,
    brandError: (BrandError) -> E
): RemoteResponse<T, E> {
    return safeApiCall(
        response = response,
        bodyToRemote = {
            it.toRemoteResponse(remoteError, brandError)
        },
        remoteError = remoteError
    )
}

fun <T> safeApiResponseCall(
    response: Response<ApiResponse<T>>
): RemoteResponse<T, Exception> {
    return safeApiResponseCall(
        response = response,
        remoteError = {
            it.asException()
        },
        brandError = {
            Exception("${it.code}: ${it.msg}")
        }
    )
}

fun <T, R, E> safeApiCallAllowEmptyBody(
    response: Response<T>,
    bodyToRemote: (T?) -> RemoteResponse<R?, E>,
    remoteError: (RemoteError) -> E
): RemoteResponse<R?, E> {
    return try {
        response.toRemoteResponseAllowEmptyBody(bodyToRemote, remoteError)
    } catch (e: Exception) {
        RemoteResponse.createError(remoteError(RemoteError.General(e)))
    }
}