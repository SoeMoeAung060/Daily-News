package com.soe.dailynews.domain.model

data class NetworkError(
    val error : ApiError,
    val throwable: Throwable? = null
)


enum class ApiError(val message : String){

    NetworkError("Network Error"),
    UnknownError("Unknown Error"),
    UnknownResponseError("Unknown Response Error"),
}