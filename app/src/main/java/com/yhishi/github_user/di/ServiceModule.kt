package com.yhishi.github_user.di

import androidx.databinding.ktx.BuildConfig
import com.squareup.moshi.Moshi
import com.yhishi.github_user.R
import com.yhishi.github_user.domain.repository.api.retrofit.RetrofitService
import com.yhishi.github_user.util.ApplicationResources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
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
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }
            .build()
    }

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
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()).asLenient())
            .client(client)
            .build()
            .create(RetrofitService::class.java)
    }

}
