package com.example.rapgenerator.data.usecase

import com.example.rapgenerator.data.network.repoImpl.UberduckRepositoryImpl
import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.model.beat.BeatResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeatsUseCase @Inject constructor(private val repo : UberduckRepositoryImpl){
    operator fun invoke() : Flow<NetworkResponse<BeatResponse>> = repo.getBeat()
}