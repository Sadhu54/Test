package com.test_app_21.network

import com.test_app_21.utils.TestConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitBuilder {

    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder().followRedirects(false).followSslRedirects(false).apply {

            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            connectTimeout(7, TimeUnit.MINUTES)
            readTimeout(7, TimeUnit.MINUTES)
        }.build()

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): ApiInterface =
        Retrofit.Builder().apply {
            baseUrl(TestConstants.URL.BASE_URL)
            addConverterFactory(gsonConverterFactory)
            client(okHttpClient)
        }.build().create(ApiInterface::class.java)
}