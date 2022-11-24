package com.sensetime.nebula.webapi4j.lib

sealed class RemoteError {

    data class General(val throwable: Throwable) : RemoteError()

    fun asException(): Exception {
        return when (this) {
            is General -> Exception(this.throwable)
        }
    }

    companion object {
        fun general(throwable: Throwable): RemoteError {
            return General(throwable)
        }
    }
}