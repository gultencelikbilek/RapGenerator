package com.example.rapgenerator.data.network.repoImpl

import android.util.Log
import com.example.rapgenerator.data.network.UberduckService
import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.model.beat.BeatResponse
import com.example.rapgenerator.domain.model.beat.beat_url.BeatUrlResponse
import com.example.rapgenerator.domain.model.music.MusicRequest
import com.example.rapgenerator.domain.model.music.MusicResponse
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.model.rapper.rapper_url.RapperResponseUrlItem
import com.example.rapgenerator.domain.repository.IUberduckRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UberduckRepositoryImpl @Inject constructor(private val uberduckService: UberduckService) :
    IUberduckRepository {
    override fun getBeat(): Flow<NetworkResponse<BeatResponse>> {
        return callbackFlow {
            // Uberduck API üzerinden bir BeatResponse çağrısı oluşturuluyor.
            val responseBeat = uberduckService.getBeat()

            // Ağ çağrısı başladığı için LOADING durumunu akışa iletiyoruz.
            trySend(NetworkResponse.Loading)

            // Ağ çağrısının sonucunu dinlemek için enqueue kullanılıyor.
            responseBeat.enqueue(object : Callback<BeatResponse> {
                // Başarılı bir cevap alındığında onResponse çağrılır.
                override fun onResponse(
                    call: Call<BeatResponse>,
                    response: Response<BeatResponse>
                ) {
                    // Cevap başarılı mı kontrol ediliyor.
                    if (response.isSuccessful) {
                        // Cevap alındığında cevap içeriği alınıyor.
                        val bodyBeat = response.body()
                        println("bodyBeat is $bodyBeat")

                        // Cevap içeriği null değilse, başarılı bir cevap gönderiliyor.
                        bodyBeat?.let {
                            trySend(NetworkResponse.Success(bodyBeat))
                        }
                    } else {
                        // Başarısız bir cevap durumunda hata mesajı ile birlikte Error durumu gönderiliyor.
                        trySend(NetworkResponse.Error("Error Failure"))
                    }
                }

                // Ağ çağrısı başarısız olduğunda onFailure çağrılır.
                override fun onFailure(call: Call<BeatResponse>, t: Throwable) {
                    // Hatayı yakalayıp, hata mesajı ile birlikte Error durumu gönderiliyor.
                    trySend(NetworkResponse.Error(t.message.toString()))
                }
            })

            // Akışın kapatılmasını bekleyen bir awaitClose çağrılıyor.
            awaitClose()
        }
    }

    override fun getBeatUrl(uuid: String): Flow<NetworkResponse<BeatUrlResponse>> {
        return callbackFlow {
            val responseBeatUrl = uberduckService.getBeatUrl(uuid)
            trySend(NetworkResponse.Loading)
            responseBeatUrl.enqueue(object : Callback<BeatUrlResponse> {
                override fun onResponse(
                    call: Call<BeatUrlResponse>,
                    response: Response<BeatUrlResponse>
                ) {
                    if (response.isSuccessful) {
                        val bodyBeatUrl = response.body()
                        bodyBeatUrl?.let {
                            trySend(NetworkResponse.Success(bodyBeatUrl))
                        }
                    } else {
                        trySend(NetworkResponse.Error("error Failure"))
                    }
                }

                override fun onFailure(call: Call<BeatUrlResponse>, t: Throwable) {
                    trySend(NetworkResponse.Error(t.message))
                }
            })
            awaitClose()
        }
    }

    override fun getRapper(): Flow<NetworkResponse<RapperResponse>> {
        return callbackFlow {
            val responseRapper = uberduckService.getRapper()
            trySend(NetworkResponse.Loading)
            responseRapper.enqueue(object : Callback<RapperResponse> {
                override fun onResponse(
                    call: Call<RapperResponse>,
                    response: Response<RapperResponse>
                ) {
                    if (response.isSuccessful) {
                        val bodyRapper = response.body()
                        Log.d("uberduckRepositoryImpl:", bodyRapper.toString())
                        bodyRapper?.let {
                            trySend(NetworkResponse.Success(bodyRapper))
                        }
                    } else {
                        Log.d("uberduckRepositoryImpl:", "bodyRapper")

                        trySend(NetworkResponse.Error("errorRepositoryImpl"))
                    }
                }

                override fun onFailure(call: Call<RapperResponse>, t: Throwable) {
                    Log.d("onFailure:UberduckRepositoryImpl:", t.message.toString())
                }
            })
            awaitClose()
        }
    }


    override  fun getRapperUlr(id: String): Flow<NetworkResponse<RapperResponseUrlItem>> {
        return callbackFlow {
            val responseRapperUrl = uberduckService.getRapperUrl(id)
            trySend(NetworkResponse.Loading)
            responseRapperUrl.enqueue(object : Callback<RapperResponseUrlItem> {
                override fun onResponse(
                    call: Call<RapperResponseUrlItem>,
                    response: Response<RapperResponseUrlItem>
                ) {
                    if (response.isSuccessful) {
                        val bodyRapperUrl = response.body()
                        bodyRapperUrl?.let {
                            trySend(NetworkResponse.Success(bodyRapperUrl))
                        }
                    } else {
                        trySend(NetworkResponse.Error("onError"))
                    }
                }

                override fun onFailure(call: Call<RapperResponseUrlItem>, t: Throwable) {
                    trySend(NetworkResponse.Error(t.message))
                }
            })
            awaitClose()
        }
    }

    override suspend fun sendRapper(musicRequest: MusicRequest): Response<MusicResponse> {
        return uberduckService.postFreestyle(musicRequest)
    }
}