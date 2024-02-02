package com.example.rapgenerator.di

import com.example.rapgenerator.api.ApiService
import com.example.rapgenerator.di.repository.PromptsRepositoryImpl
import com.example.rapgenerator.prompts.repository.IPromptRepository
import com.example.rapgenerator.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
//SingletonComponent kullanılmış, bu da modülün uygulama boyunca tek bir örneği olacağını belirtir.
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton //@Singleton, ilgili bağımlılığın uygulama ömrü boyunca sadece bir kez oluşturulacağını belirtir.
    @Provides
    fun getClient() : ApiService{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
            .create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideRepositoryImpl(
        apiService: ApiService
    ) : IPromptRepository = PromptsRepositoryImpl(apiService)
}