package com.example.rapgenerator.di

import com.example.rapgenerator.api.ApiService
import com.example.rapgenerator.di.repository.PromptsRepositoryImpl
import com.example.rapgenerator.data.repository.IPromptRepository
import com.example.rapgenerator.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
//SingletonComponent kullanılmış, bu da modülün uygulama boyunca tek bir örneği olacağını belirtir.
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): ApiService{
       return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun providesBeatRetrofit() : ApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BEAT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryImpl(
        apiService: ApiService
    ): IPromptRepository = PromptsRepositoryImpl(apiService)
}