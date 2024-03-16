package com.example.rapgenerator.presentation.rapper

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.data.usecase.GetRapperUrlUseCase
import com.example.rapgenerator.data.usecase.GetRapperUseCase
import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.ResponseStatus
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.model.rapper.RapperUrlResponse
import com.example.rapgenerator.domain.model.rapper.RapperUrlResponseItem
import com.example.rapgenerator.domain.model.rapper.rapper_url.RapperResponseUrlItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RapperViewModel @Inject constructor(
    val getRapperUseCase: GetRapperUseCase,
    val getRapperUrlUseCase: GetRapperUrlUseCase
) : ViewModel() {

    private val _rapperUrl = MutableStateFlow<List<RapperUrlResponse?>>(emptyList())
    val rapperUrl: StateFlow<List<RapperUrlResponse?>> = _rapperUrl

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _rapperResponse = MutableStateFlow<RapperResponse?>(null)
    val rapperResponse: StateFlow<RapperResponse?> = _rapperResponse

    fun getRapper() = viewModelScope.launch {
        getRapperUseCase.invoke().collect { response ->
            when (response) {
                is NetworkResponse.Loading -> {
                    _isLoading.value = true
                }

                is NetworkResponse.Success -> {
                    response.data?.let {
                        _rapperResponse.value = it
                        Log.d("Uberduck:Succes:Rapper", response.message.toString())

                    }
                    _isLoading.value = false
                }

                is NetworkResponse.Error -> {
                    Log.d("Uberduck:Exc:Rapper", response.message ?: "Unknown error")
                }
            }
        }
    }

    suspend fun getRapperUrl(uuid: String) {
        Log.d("RapperFragment1", "UUID: $uuid")
        try {
            getRapperUrlUseCase.invoke(uuid = uuid).collect { response ->
                Log.d("RapperFragment2", "UUID: $uuid")
                when (response.responseStatus) {
                    ResponseStatus.LOADING -> {
                        _isLoading.value = true
                    }
                    ResponseStatus.SUCCESS -> {
                        response.data.let {
                            _rapperUrl.value = listOf(it)
                            Log.d("Uberduck:Succes:RapperUrl", response.message ?: "Success")
                        }
                    }
                    ResponseStatus.ERROR -> {
                        Log.d("Uberduck:Exc:RapperUrl", response.message ?: "Unknown error")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("RapperFragment", "Error occurred while fetching rapper URL: ${e.message}")
            // Hata durumunu işleyin, isteğin neden başarısız olduğunu belirleyin
        }
    }
}
