package com.sensetime.nebula.webapi4j.lib.base


sealed class RemoteResponse<T, E> {

    data class Success<T, E>(val body: T) : RemoteResponse<T, E>()

    data class Error<T, E>(val error: E) : RemoteResponse<T, E>()

    fun <R> map(mapper: (T) -> R): RemoteResponse<R, E> {
        return when (this) {
            is Success -> createSuccess(mapper(body))
            is Error -> createError(error)
        }
    }

    fun <R> mapError(mapper: (E) -> R): RemoteResponse<T, R> {
        return when (this) {
            is Success -> createSuccess(body)
            is Error -> createError(mapper(error))
        }
    }

    companion object {
        fun <T, E> createError(error: E): RemoteResponse<T, E> {
            return Error(error)
        }

        fun <T, E> createSuccess(data: T): RemoteResponse<T, E> {
            return Success(data)
        }
    }
}