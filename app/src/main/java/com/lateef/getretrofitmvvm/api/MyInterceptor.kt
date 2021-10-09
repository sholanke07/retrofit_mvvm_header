package com.lateef.getretrofitmvvm.api

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor{
    //using header
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Platform", "Android")
                .addHeader("X-Auth-Token", "123456789")
                .build()
        return chain.proceed(request)
    }
}