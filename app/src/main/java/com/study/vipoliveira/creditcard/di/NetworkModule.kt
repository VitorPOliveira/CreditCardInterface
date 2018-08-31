package com.study.vipoliveira.creditcard.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.study.vipoliveira.creditcard.service.PaymentService
import com.study.vipoliveira.creditcard.utils.BASE_URL
import com.study.vipoliveira.creditcard.utils.ML_KEY_NAME
import com.study.vipoliveira.creditcard.utils.ML_PUBLIC_KEY
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(ML_KEY_NAME, ML_PUBLIC_KEY)
                    .build()

            chain.proceed(original.newBuilder().url(url).build())
        }

        return httpClient.build()
    }
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build()
    }

    @Provides
    fun providePaymentService(retrofit: Retrofit) : PaymentService {
        return retrofit.create<PaymentService>(PaymentService::class.java)
    }

}
