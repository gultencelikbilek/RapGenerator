package com.example.rapgenerator.data.usecase

import com.example.rapgenerator.data.network.repoImpl.UberduckRepositoryImpl
import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.model.rapper.rapper_url.RapperResponseUrlItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRapperUrlUseCase @Inject constructor(private val repositoryImpl: UberduckRepositoryImpl) {
    operator fun invoke(uuid:String) : Flow<NetworkResponse<RapperResponseUrlItem>> = repositoryImpl.getRapperUlr(id =uuid)
}