package uba.fi.goodreads.core.network

sealed class NetworkResult<out T> {
    data class Success<out T>(val value: T) : NetworkResult<T>()

    open class ErrorBase(
        open val title: String? = null,
        open val status: Int? = null,
        open val detail: String? = null,
        open val instance: String? = null,
    ) : NetworkResult<Nothing>()

    class NotFound(
        title: String? = null,
        status: Int? = null,
        detail: String? = null,
        instance: String? = null,
    ) : ErrorBase(title, status, detail, instance)

    class Conflict(
        title: String? = null,
        status: Int? = null,
        detail: String? = null,
        instance: String? = null,
    ) : ErrorBase(title, status, detail, instance)

    class GenericError(
        title: String? = null,
        status: Int? = null,
        detail: String? = null,
        instance: String? = null,
    ) : ErrorBase(title, status, detail, instance)

    data object NetworkError : NetworkResult<Nothing>()
    data object LocalError : NetworkResult<Nothing>()
}