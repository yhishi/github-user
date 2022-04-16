package com.yhishi.github_user.di

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.ktx.BuildConfig
import com.squareup.moshi.Moshi
import com.yhishi.github_user.R
import com.yhishi.github_user.domain.model.api.ZonedDateTimeAdapter
import com.yhishi.github_user.domain.repository.api.retrofit.RetrofitService
import com.yhishi.github_user.util.ApplicationResources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Named("Client")
    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val token = com.yhishi.github_user.BuildConfig.GITHUB_TOKEN
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .authenticator { _, response ->
                response.request.newBuilder().header("Authorization", "Bearer $token").build()
            }
            .addInterceptor(Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            })
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun providesRetrofitService(
        resources: ApplicationResources,
        @Named("Client")
        client: OkHttpClient,
    ): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(resources.getString(R.string.api_url))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(ZonedDateTime::class.java, ZonedDateTimeAdapter.nullSafe()).build()
                )
            )
            .client(client)
            .build()
            .create(RetrofitService::class.java)
    }

}
