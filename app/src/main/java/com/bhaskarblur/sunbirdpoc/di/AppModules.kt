package com.bhaskarblur.sunbirdpoc.di

import android.content.Context
import com.bhaskarblur.gptbot.network.LoggingInterceptor
import com.bhaskarblur.gptbot.network.NormalHeaderInterceptor
import com.bhaskarblur.gptbot.network.TemplateHeaderInterceptor
import com.bhaskarblur.sunbirdpoc.core.constants.ApiConstants.Credential_Base_Url
import com.bhaskarblur.sunbirdpoc.data.local.LocalStorageManager
import com.bhaskarblur.sunbirdpoc.data.repository.FunctionalityRepository
import com.bhaskarblur.sunbirdpoc.presentation.ActivityViewModel
import com.bhaskarblur.sync_realtimecontentwriting.data.remote.ApiRoutes
import com.bhaskarblur.sync_realtimecontentwriting.data.remote.utils.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Singleton
    @Provides
    fun okHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // Change it as per your requirement
            .readTimeout(5, TimeUnit.MINUTES)// Change it as per your requirement
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .addInterceptor(LoggingInterceptor())
            .build()
    }

    @Singleton
    @Provides
    @Named("templateApiClient")
    fun templateApiClient(@ApplicationContext context: Context): Retrofit = ApiClient.getInstance(
        okHttpClient((TemplateHeaderInterceptor(context))),
        Credential_Base_Url
    )

    @Singleton
    @Provides
    @Named("regularApiClient")
    fun regularApiClient(@ApplicationContext context: Context): Retrofit = ApiClient.getInstance(
        okHttpClient((NormalHeaderInterceptor(context))),
        Credential_Base_Url
    )

    @Provides
    @Singleton
    @Named("regularApiRoute")
    fun returnRegularApiRoutes(@Named("regularApiClient") apiClient: Retrofit): ApiRoutes =
        apiClient.create(ApiRoutes::class.java)

    @Provides
    @Singleton
    @Named("templateApiRoute")
    fun returnTemplateApiRoutes(@Named("templateApiClient")apiClient: Retrofit): ApiRoutes =
        apiClient.create(ApiRoutes::class.java)


    @Provides
    @Singleton
    fun providesLocalStorageManager(@ApplicationContext context : Context) : LocalStorageManager = LocalStorageManager(context)

    @Provides
    @Singleton
    fun providesFunctionalRepo(@Named("regularApiRoute") regAiRoutes: ApiRoutes,
                               @Named("templateApiRoute") tempApiRoute : ApiRoutes,
                               storageManager: LocalStorageManager) : FunctionalityRepository {
        return FunctionalityRepository(regAiRoutes, tempApiRoute, storageManager)
    }

    @Provides
    @Singleton
    fun providesViewModel(repo : FunctionalityRepository) : ActivityViewModel {
        return ActivityViewModel(repo)
    }
}