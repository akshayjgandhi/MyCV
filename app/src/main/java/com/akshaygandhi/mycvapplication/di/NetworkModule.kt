package com.akshaygandhi.mycvapplication.di

import com.akshaygandhi.mycvapplication.BuildConfig
import com.akshaygandhi.mycvapplication.data.CVApiService
import com.akshaygandhi.mycvapplication.rx.IoThreadScheduler
import com.akshaygandhi.mycvapplication.rx.SchedulerProvider
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
object NetworkModule {

    private fun provideGsonFactoryConvertor(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        return GsonConverterFactory.create(gsonBuilder.setLenient().create())
    }

    private fun provideRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        val client = OkHttpClient.Builder()


        val builder = Retrofit.Builder()
            .client(client.build())
            .addCallAdapterFactory(callAdapterFactory)
        return builder.baseUrl(baseUrl).addConverterFactory(converterFactory).build()
    }

    @Provides
    @JvmStatic
    fun provideRxJava2CallAdapterFactory(
        @IoThreadScheduler schedulerProvider: SchedulerProvider
    ): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory
            .createWithScheduler(schedulerProvider.provideSchedulerProvider())
    }

    @Provides
    @JvmStatic
    fun provideCVApiService(
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): CVApiService {
        return provideRetrofit(
            BuildConfig.API_BASE_URL,
            provideGsonFactoryConvertor(),
            rxJava2CallAdapterFactory
        ).create<CVApiService>(CVApiService::class.java)
    }

}
