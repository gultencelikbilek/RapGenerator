package com.example.rapgenerator.data.usecase

import com.example.rapgenerator.data.network.repoImpl.UberduckRepositoryImpl
import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.model.music.MusicRequest
import com.example.rapgenerator.domain.model.music.MusicResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostFreestleUseCase @Inject constructor(private val repositoryImpl: UberduckRepositoryImpl) {
    operator fun invoke(musicRequest: MusicRequest) : Flow<NetworkResponse<MusicResponse>> = repositoryImpl.postFreestyle(musicRequest)
}