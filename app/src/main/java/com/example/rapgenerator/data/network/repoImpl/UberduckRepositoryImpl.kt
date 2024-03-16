package com.example.rapgenerator.data.network.repoImpl

import android.util.Log
import com.example.rapgenerator.data.network.UberduckService
import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.model.beat.BeatResponse
import com.example.rapgenerator.domain.model.beat.beat_url.BeatUrlResponse
import com.example.rapgenerator.domain.model.music.MusicRequest
import com.example.rapgenerator.domain.model.music.MusicResponse
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.model.rapper.RapperUrlResponse
import com.example.rapgenerator.domain.model.rapper.RapperUrlResponseItem
import com.example.rapgenerator.domain.model.rapper.rapper_url.RapperResponseUrlItem
import com.example.rapgenerator.domain.repository.IUberduckRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

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


    override  fun getRapperUrl(id: String): Flow<NetworkResponse<RapperUrlResponse>> {
        return callbackFlow {
            val responseRapperUrl = uberduckService.getRapperUrl(id)
            trySend(NetworkResponse.Loading)
            responseRapperUrl.enqueue(object : Callback<RapperUrlResponse> {
                override fun onResponse(
                    call: Call<RapperUrlResponse>,
                    response: Response<RapperUrlResponse>
                ) {
                    if (response.isSuccessful) {
                        val bodyRapperUrl = response.body()
                        bodyRapperUrl?.let {
                            trySend(NetworkResponse.Success(bodyRapperUrl))
                            Log.d("RapperFragment:Success:", bodyRapperUrl.toString())
                        }
                    } else {
                        trySend(NetworkResponse.Error("onError"))
                    }
                }



                override fun onFailure(call: Call<RapperUrlResponse>, t: Throwable) {
                        Log.e("RapperFragment", "Error occurred while fetching rapper URL: ${t.message}")
                        trySend(NetworkResponse.Error(t.message))
                }
            })
            awaitClose()
        }
    }

    override fun postFreestyle(musicRequest: MusicRequest): Flow<NetworkResponse<MusicResponse>> {
        return callbackFlow {
            trySend(NetworkResponse.Loading) // Akışın başlangıcında Loading durumunu gönderin

            val responseFreestyle = uberduckService.postFreestyle(musicRequest)
            responseFreestyle.enqueue(object : Callback<MusicResponse> {
                override fun onResponse(
                    call: Call<MusicResponse>,
                    response: Response<MusicResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.d("onResponse:UberduckRepo:", it.mix_url)
                            trySend(NetworkResponse.Success(it))
                        } ?: trySend(NetworkResponse.Error("onResponse:bodyFreestyle - Body is null"))
                    } else {
                        trySend(NetworkResponse.Error("Response unsuccessful: ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    trySend(NetworkResponse.Error("onFailure: ${t.message}"))
                }
            })
            awaitClose()
        }
    }
}