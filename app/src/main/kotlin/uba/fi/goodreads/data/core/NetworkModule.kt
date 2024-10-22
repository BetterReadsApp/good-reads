package uba.fi.goodreads.data.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import uba.fi.goodreads.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideBetterReadsClient(
        retrofitBuilder: RetrofitBuilder
    ): BetterReadsClient {
        return retrofitBuilder.build(

        ).create(BetterReadsClient::class.java)
    }


    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(tokenInterceptor: TokenInterceptor): Call.Factory =
        OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            // .addInterceptor(securityTokensInterceptor)
            // TODO Datadog .eventListenerFactory(DatadogEventListener.Factory())
            .build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        networkJson: Json,
        okhttpCallFactory: dagger.Lazy<Call.Factory>,
    ): RetrofitBuilder {
        return RetrofitBuilder(
            baseUrl = BuildConfig.BASE_URL,
            networkJson = networkJson,
            okhttpCallFactory = okhttpCallFactory
        )
    }

    @Provides
    @Singleton
    fun provideResponseHandler(
        @Dispatcher(BrDispatchers.IO) ioDispatcher: CoroutineDispatcher,
    ): ResponseHandler {
        return ResponseHandler(
            ioDispatcher
        )
    }
}