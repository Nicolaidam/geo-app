package dk.example.geoapp.model

import io.ktor.http.HttpStatusCode

sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Exception) : Response<Nothing>()
}

class HttpException(val statusCode: HttpStatusCode) : Exception()
