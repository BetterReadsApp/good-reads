package uba.fi.goodreads.data.core

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import uba.fi.goodreads.core.network.CONFLICT
import uba.fi.goodreads.core.network.LogoutException
import uba.fi.goodreads.core.network.NOT_FOUND
import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.core.network.UNAUTHORIZED
import java.io.IOException

class ResponseHandler(
    private val dispatcher: CoroutineDispatcher
) {

    private val _unauthorizedFlow = MutableSharedFlow<Boolean>()
    val unauthorizedFlow = _unauthorizedFlow.asSharedFlow()

    suspend operator fun <T : Any> invoke(
        apiCall: suspend () -> T
    ): NetworkResult<T> {
        return withContext(dispatcher) {
            try {
                NetworkResult.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        if (throwable is LogoutException)
                            NetworkResult.LogOut
                        else
                            NetworkResult.NetworkError
                    }

                    is HttpException -> {
                        val code = throwable.code()
                        val errorBody = getErrorBody(throwable)
                        when (code) {
                            UNAUTHORIZED -> {
                                _unauthorizedFlow.emit(true)
                                NetworkResult.LogOut
                            }
                            NOT_FOUND -> NetworkResult.NotFound(
                                title = errorBody?.title,
                                status = code,
                                detail = errorBody?.detail,
                                instance = errorBody?.instance,
                            )
                            CONFLICT -> NetworkResult.Conflict(
                                title = errorBody?.title,
                                status = code,
                                detail = errorBody?.detail,
                                instance = errorBody?.instance,
                            )
                            else -> NetworkResult.GenericError(
                                title = errorBody?.title,
                                status = code,
                                detail = errorBody?.detail,
                                instance = errorBody?.instance,
                            )
                        }
                    }

                    is NullPointerException -> {
                        Log.e(TAG, "NullPointerException ${throwable.message.toString()}")
                        NetworkResult.LocalError
                    }

                    is NumberFormatException -> {
                        Log.e(TAG, "NumberFormatException ${throwable.message.toString()}")
                        NetworkResult.LocalError
                    }

                    else -> {
                        Log.e(TAG, throwable.message.toString())
                        NetworkResult.LocalError
                    }
                }
            }
        }
    }

    private fun getErrorBody(throwable: HttpException): ApiErrorResponse? {
        val errorString = throwable.response()?.errorBody()?.string() ?: ""
        return try {
            Json.decodeFromString<ApiErrorResponse>(errorString)
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private const val TAG = "ResponseHandler"
    }
}