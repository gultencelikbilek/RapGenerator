package com.example.rapgenerator.domain.di


import com.example.rapgenerator.data.network.UberduckService
import com.example.rapgenerator.data.network.GptService
import com.example.rapgenerator.data.network.repoImpl.GptRepositoryImpl
import com.example.rapgenerator.data.network.repoImpl.UberduckRepositoryImpl
import com.example.rapgenerator.domain.repository.IGptRepository
import com.example.rapgenerator.domain.repository.IUberduckRepository
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
    fun provideRetrofit(): GptService {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GptService::class.java)
    }


    @Singleton
    @Provides
    fun providesBeatRetrofit(): UberduckService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_BEAT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UberduckService::class.java)
    }

    @Provides
    @Singleton
    fun provideGptRepositoryImpl(
        gptService: GptService
    ): IGptRepository  = GptRepositoryImpl(gptService)

    @Provides
    @Singleton
    fun provideUberduckRepositoryImpl(
        uberduckService: UberduckService
    ): IUberduckRepository = UberduckRepositoryImpl(uberduckService)
}