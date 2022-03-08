package com.lyhv.mvvm.di

import com.lyhv.mvvm.BuildConfig
import com.lyhv.mvvm.core.api.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Suppress("unused")
@Module(includes = [CoreDataModule::class])
@InstallIn(SingletonComponent::class)
class NetworkModule {
    companion object {
        private val LIST_PUBLIC_APIS = listOf(
            "products",
            "tokens"
        )

        fun isPublicApi(urlPath: String): Boolean {
            return LIST_PUBLIC_APIS.any { urlPath.contains(it) }
        }
    }

    @Provides
    fun provideRetrofit(
        @MoshiBase moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiService.ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    internal fun provideService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideOkHttpClient(
        @BasicAuthInterceptorOkHttpClient
        basicAuth: Interceptor,
        @TokenAuthInterceptorOkHttpClient
        tokenAuth: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenAuth)
            .addInterceptor(basicAuth)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @BasicAuthInterceptorOkHttpClient
    @Provides
    fun provideInterceptorBaseAuth(): Interceptor {
        val credentials: String =
            Credentials.basic(BuildConfig.USER_BASIC_AUTH, BuildConfig.PASSWORD_BASIC_AUTH)
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
                .header("Authorization", credentials)
            it.proceed(requestBuilder.build())
        }
    }

    @TokenAuthInterceptorOkHttpClient
    @Provides
    fun provideInterceptorCommon(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("X-HTTP-ServiceToken", BuildConfig.X_HTTP_SERVICETOKEN)
            it.proceed(requestBuilder.build())
        }
    }

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
}