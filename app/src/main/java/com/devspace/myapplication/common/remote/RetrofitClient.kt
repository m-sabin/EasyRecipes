package com.devspace.myapplication.common.remote

import com.devspace.myapplication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.spoonacular.com/"

    private val httpClient: OkHttpClient
        get() {
            val clientBuilder = OkHttpClient.Builder()
            val token = BuildConfig.API_KEY

            clientBuilder.addInterceptor { chain ->
                val original: Request = chain.request()
                val request = original.newBuilder()
                val originalHttpClient = chain.request().url
                val newUrl = originalHttpClient.newBuilder()
                    .addQueryParameter("apiKey", token).build()
                chain.proceed(request.url(newUrl).build())
            }

            return clientBuilder.build()
        }

    val retrofitInstance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}