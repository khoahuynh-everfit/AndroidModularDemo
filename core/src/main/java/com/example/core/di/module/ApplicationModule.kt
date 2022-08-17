package com.example.core.di.module

import android.app.Application
import android.content.Context
import com.example.core.BuildConfig
import com.example.core.CoreApplication
import com.example.data.repository.authentication.AuthenticationRepository
import com.example.domain.network.api.authentication.AuthenticationAPI
import com.example.domain.repositoryiml.AuthenticationRepositoryImpl
import com.example.domain.storage.preferences.PreferenceHelper
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApplication(application: Application): CoreApplication {
        return application as CoreApplication
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(context: Context): PreferenceHelper {
        return PreferenceHelper(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api-dev.everfit.io/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideAuthorizationAPI(retrofit: Retrofit): AuthenticationAPI = retrofit.create(
        AuthenticationAPI::class.java)

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        authenticationAPI: AuthenticationAPI,
        preferenceHelper: PreferenceHelper
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(authenticationAPI, preferenceHelper)
    }
}