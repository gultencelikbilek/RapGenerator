import RapperResponseItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.di.AppModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RapperViewModel @Inject constructor() : ViewModel() {

    private val _rapperResponse = MutableLiveData<RapperResponseItem>()
    val rapperResponse: LiveData<RapperResponseItem> = _rapperResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

  // fun getRapper() {
  //     viewModelScope.launch {
  //         try {
  //             // API'den rapçi verilerini al
  //             val response = AppModule.providesBeatRetrofit().getRapper()

  //             // HTTP yanıt kodunu kontrol et
  //             if (response.code() == 200) { // 200 başarılı yanıtı temsil eder
  //                 // Yanıt başarılı ise, ResponseBody'yi al ve işle
  //                 val responseBody = response.body()
  //                 // responseBody'yi uygun şekilde işleyin ve _rapperResponse'a ata
  //             } else {
  //                 // Başarısız yanıt durumunda hata mesajını yayınla
  //                 _error.value = "Error: ${response.message()}"
  //             }
  //         } catch (e: Exception) {
  //             // Hata durumunda hata mesajını yayınla
  //             _error.value = "Error: ${e.message}"
  //         }
  //     }
  // }

}
