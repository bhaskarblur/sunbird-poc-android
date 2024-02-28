package com.bhaskarblur.gptbot.network

import android.content.Context
import android.util.Log
import com.bhaskarblur.sync_realtimecontentwriting.data.remote.ApiRoutes
import okhttp3.Interceptor
import okhttp3.Response

class TemplateHeaderInterceptor(
    private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request();
        val authenticatedRequest = request.newBuilder()
            .addHeader("Accept", "application/pdf")
            .addHeader("templateId", "clt4oxn2f0004s379825521id")
            .build()

        return chain.proceed(authenticatedRequest);
    }
}

class NormalHeaderInterceptor(
    private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request();
        val authenticatedRequest = request.newBuilder()
            .build()

        return chain.proceed(authenticatedRequest);
    }
}