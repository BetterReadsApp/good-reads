package uba.fi.goodreads.data.core

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitBuilder @Inject constructor(
    private val baseUrl: String,
    private val networkJson: Json,
    // private val securityTokensInterceptor: SecurityTokensInterceptor,
    private val okhttpCallFactory: dagger.Lazy<Call.Factory>,
) {

    fun build(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
    }
}