package com.lateef.getretrofitmvvm.util

import com.google.gson.Gson
import com.lateef.getretrofitmvvm.api.MyInterceptor
import com.lateef.getretrofitmvvm.api.SimpleApi
import com.lateef.getretrofitmvvm.util.Constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}