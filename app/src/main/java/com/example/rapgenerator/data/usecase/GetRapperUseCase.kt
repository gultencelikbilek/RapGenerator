package com.example.rapgenerator.data.usecase

import com.example.rapgenerator.data.network.repoImpl.UberduckRepositoryImpl
import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.model.rapper.RapperResponseItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRapperUseCase @Inject constructor(private val repositoryImpl: UberduckRepositoryImpl) {
     operator fun invoke() : Flow<NetworkResponse<RapperResponse>> = repositoryImpl.getRapper()
}