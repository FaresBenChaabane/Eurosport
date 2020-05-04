package fr.benchaabane.test.network

import fr.benchaabane.test.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

fun OkHttpClient.Builder.buildHttpClient(cache: Cache,
                                         networkConfig: NetworkConfig,
                                         headerInterceptor: HeaderInterceptor? = null): OkHttpClient {
    cache(cache)
    connectTimeout(networkConfig.connectionTimeOut, TimeUnit.MILLISECONDS)
    readTimeout(networkConfig.socketTimeOut, TimeUnit.MILLISECONDS)
    headerInterceptor?.let { addNetworkInterceptor(it) }

    if (BuildConfig.LOG_ENABLE) {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(logInterceptor)
    }

    return build()
}



