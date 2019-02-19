package com.bud.app.di

import com.bud.app.BuildConfig
import com.bud.app.core.api.AppService
import com.bud.app.di.qualifiers.AccessToken
import com.bud.app.di.qualifiers.BaseUrl
import com.bud.app.di.scopes.PerApplication
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @PerApplication
    @Provides
    fun provideApiService(
        @BaseUrl baseUrl: String, client: OkHttpClient
    ): AppService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppService::class.java)


    @PerApplication
    @Provides
    fun providesHttpClient(interceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(logging)
            .build()
    }

    @PerApplication
    @Provides
    fun providesAccessTokenInterceptor(@AccessToken accessToken: String): Interceptor =
        Interceptor {
            val request = it.request().newBuilder()
                .addHeader("Authorization", "bwt $accessToken").build()
            it.proceed(request)
        }

    @PerApplication
    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}