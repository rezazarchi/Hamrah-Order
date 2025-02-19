package ir.rezazarchi.hamrahorder.core.maputils

import ir.rezazarchi.hamrahorder.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module


const val MAP_CLIENT = "OK_HTTP_MAP_IR"

val mapNetworkModule = module {
    single(named(MAP_CLIENT)) {
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .header("x-api-key", BuildConfig.MAPIR_API_KEY)
                        .build()
                )
            })
            .build()
    }
}