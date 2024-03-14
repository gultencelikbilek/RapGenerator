package com.example.rapgenerator.data.usecase

import com.example.rapgenerator.data.network.repoImpl.UberduckRepositoryImpl
import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.model.beat.beat_url.BeatUrlResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeatUrlUseCase @Inject constructor(private val repo : UberduckRepositoryImpl){
    operator fun invoke(uuid:String) : Flow<NetworkResponse<BeatUrlResponse>> = repo.getBeatUrl(uuid)
}